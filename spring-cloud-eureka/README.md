spring-cloud-eureka-server

spring-cloud是基于spring-boot的云应用开发工具，为构建微服务提供了一系列开发工具

例如：配置管理、服务发现、断路器、智能路由、微代理、控制总线、全局锁

eureka注册中心服务端

这是一个注册中心的服务端，作为一个独立的springboot服务，配置较为简单

作用：服务注册、服务发现

spring-cloud对服务的治理采取特殊的方式，它为服务治理做了一层抽象接口，所以spring-cloud可以支持多种不同的服务治理框架
