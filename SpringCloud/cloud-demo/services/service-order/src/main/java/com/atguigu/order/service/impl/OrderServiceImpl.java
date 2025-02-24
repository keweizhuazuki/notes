package com.atguigu.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.order.bean.Order;
import com.atguigu.order.feign.ProductFeignClient;
import com.atguigu.order.service.OrderService;
import com.atguigu.product.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    ProductFeignClient productFeignClient;

    @SentinelResource(value = "createOrder", blockHandler = "createOrderFallback")
    @Override
    public Order createOrder(Long productId, Long userId) {
        //使用RestTemplate远程调用
        //Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);
        //使用feign远程调用
        Product product = productFeignClient.getProductById(productId);
        Order order = new Order();
        order.setId(1L);
        //订单总金额
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("zhangsan");
        order.setAddress("尚硅谷");
        //远程调用商品服务获取商品信息
        order.setProductList(Arrays.asList(product));
        return order;
    }

    public Order createOrderFallback(Long productId, Long userId, BlockException e) {
        Order order = new Order();
        order.setId(0L);
        order.setTotalAmount(new BigDecimal("0"));
        order.setUserId(userId);
        order.setNickName("位置用户");
        order.setAddress("异常信息：" + e.getClass());
        return order;
    }

    //普通的远程调用
    private Product getProductFromRemote(Long productId) {
        //获取商品服务所在的所有机器ip+端口
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        //远程yrl
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        //给远程发送请求
        return restTemplate.getForObject(url, Product.class);
    }

    //进阶版：负载均衡的远程调用
    private Product getProductFromRemoteWithLoadBalancer(Long productId) {
        //获取商品服务所在的所有机器ip+端口
        ServiceInstance instance = loadBalancerClient.choose("service-product");
        //远程yrl
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        //给远程发送请求
        return restTemplate.getForObject(url, Product.class);
    }

    //进阶版2：基于注解的负载均衡
    private Product getProductFromRemoteWithLoadBalancerAnnotation(Long productId) {
        String url = "http://service-product/product/" + productId;
        //给远程发送请求 service-product会被动态替换为具体的ip+端口
        return restTemplate.getForObject(url, Product.class);
    }
}
