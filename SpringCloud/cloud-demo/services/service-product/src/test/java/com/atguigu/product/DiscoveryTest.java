package com.atguigu.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    DiscoveryClient discoveryClient;


    @Test
    void discoveryClientTest(){
        for (String service : discoveryClient.getServices()) {//获取所有服务
            System.out.println("service = "+service);
            //获取服务ip和端口
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println("ip = " + instance.getHost());
                System.out.println("port = " + instance.getPort());
            }
        }
    }
}
