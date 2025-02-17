#### Spring Cloud
- 分布式微服务架构：
  - 服务注册与发现：
  - ![alt text](image-1.png)
  - 当用户想要获取订单服务时，首先会通过服务注册中心获取服务的地址，然后通过负载均衡器选择一个服务节点进行访问。
  - ![alt text](image-2.png)
- 环境准备
  - ![alt text](image-3.png)
  - ![alt text](image-4.png)
##### Nacos
- 架构：
  - ![alt text](image-5.png)
- 下载：
  - https://nacos.io/download/release-history/?spm=5238cd80.c984973.0.0.6be14023VdL1Gl
  - 进入bin目录，执行命令：`startup.cmd -m standalone`：启动nacos单机模式
- 服务注册流程
  - ![alt text](image-6.png)
- 服务发现：
  - 开启服务发现功能：`@EnableDiscoveryClient`
  - api：`DiscoveryClient`：所有服务发现都有的接口
  - api：`NacosServiceDiscovery`:只有nacos才有的服务发现
##### OpenFeign
##### Sentinel
##### Gateway
##### Seata