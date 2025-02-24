package com.atguigu.order.feign;

import com.atguigu.order.fallback.ProductFeignClientFallback;
import com.atguigu.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class) //feign客户端
public interface ProductFeignClient {
    //两套逻辑
    //1：标记在Controller上，是接受这样的请求
    //2：标记在FeignClient上，是发起这样的请求
    @GetMapping("/api/product/{id}")
    Product getProductById(@PathVariable Long id);
}
