package com.atguigu.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.order.bean.Order;
import com.atguigu.order.properties.OrderProperties;
import com.atguigu.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/config")
    public String config() {
        return "orderTimeout = " + orderProperties.getTimeout() +
                ", orderAutoConfirm = " + orderProperties.getAutoConfirm() +
                ", orderDbUrl = " + orderProperties.getDbUrl();
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        return orderService.createOrder(productId, userId);
    }

    @GetMapping("/seckill")
    @SentinelResource(value = "seckill-order", fallback = "seckillFallback") //热点规则埋点不能与web模块的名字相同，参考notes中热点规则区
    public Order seckill(@RequestParam("userId") Long userId,
                         @RequestParam("productId") Long productId) {
        return orderService.createOrder(productId, userId);
    }

    public Order seckillFallback(Long userId, Long productId, Throwable e) {
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("异常信息： " + e.getClass());
        return order;
    }

    @GetMapping("/readDb")
    public String readDb() {
        return "readDb success";
    }
}
