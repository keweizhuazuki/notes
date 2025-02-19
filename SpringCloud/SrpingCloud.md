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
  - ![alt text](image-7.png)
- 远程调用负载均衡
  - 引入负载均衡依赖：`spring-cloud-starter-loadbalancer`
  - api：`LoadBalancerClient`：所有负载均衡都有的接口
  - 方法：`choose(String serviceId)`：负载均衡地选择一个服务实例
  - 使用注解：`@LoadBalanced`：开启负载均衡标记在RestTemplate上
- 配置中心
  - ![alt text](image-8.png)
  - 基本使用
    - ![alt text](image-9.png)
    - `@Value(“${xx}”)` 获取配置 + `@RefreshScope` 实现自动刷新
    - `@ConfigurationProperties`无感自动刷新
    - ![alt text](image-10.png)
    - 无需RefreshScope
  - NacosConfigManager监听配置变化：一旦配置发生变化，可以选择监听+通知
    - ![alt text](image-11.png)
  - 面试题：Nacos中的数据集 和 application.properties 有相同的配置项，哪个生效？
    - 应该是Nacos中的数据集生效，因为Nacos中的数据集优先级更高
  - 数据隔离
    - ![alt text](image-12.png)
    - ![alt text](image-13.png)
    - 配置为namespace为dev，group为order的common.properties和database.properties
    - ![alt text](image-14.png)
    - 不同环境的不同配置
- Nacos总结
  - ![alt text](image-15.png)
##### OpenFeign
##### Sentinel
##### Gateway
##### Seata