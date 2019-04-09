spring-cloud-eureka-client

eureka注册中心客户端，用于提供服务

可以理解为服务的提供者，并将服务注册到eureka-server注册中心

再由eureka-server提供服务的统一管理

服务提供者应该是具体业务逻辑的实现端

原理：spring-cloud是基于spring-boot构建的，服务之间是以RESTful API的方式相互调用的

对外暴露web服务，并将服务注册到eureka注册中心

服务消费者调用服务提供者暴露的web服务，但是和web service不同
