# Decisions as a Service (DaaS)

The business decisions can be modeled with DMN and these can be expose through a cloud function or a microservice. This implementation expose a [decision modeled with DMN](src/main/resources/dmn/loyalty.dmn) through [Spring Cloud Function](https://spring.io/projects/spring-cloud-function) and [Camunda DMN Engine](https://camunda.com/products/camunda-platform/dmn-engine/)

### Starting the function

```zsh
❯ ./mvnw spring-boot:run
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

2021-08-31 14:51:07.996  INFO 73769 --- [           main] com.demo.function.FunctionApplication    : Starting FunctionApplication using Java 11.0.11 on WKSAR150L.local with PID 73769 (/Users/juanolivera/Dev/uala/dmn-function/target/classes started by juanolivera in /Users/juanolivera/Dev/uala/dmn-function)
2021-08-31 14:51:07.998  INFO 73769 --- [           main] com.demo.function.FunctionApplication    : No active profile set, falling back to default profiles: default
2021-08-31 14:51:08.626  INFO 73769 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
Aug 31, 2021 2:51:08 PM org.apache.coyote.AbstractProtocol init
INFO: Initializing ProtocolHandler ["http-nio-8080"]
Aug 31, 2021 2:51:08 PM org.apache.catalina.core.StandardService startInternal
INFO: Starting service [Tomcat]
Aug 31, 2021 2:51:08 PM org.apache.catalina.core.StandardEngine startInternal
INFO: Starting Servlet engine: [Apache Tomcat/9.0.52]
Aug 31, 2021 2:51:08 PM org.apache.catalina.core.ApplicationContext log
INFO: Initializing Spring embedded WebApplicationContext
2021-08-31 14:51:08.738  INFO 73769 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 702 ms
2021-08-31 14:51:08.914  INFO 73769 --- [           main] org.camunda.feel.FeelEngine              : Engine created. [value-mapper: CompositeValueMapper(List(org.camunda.feel.impl.JavaValueMapper@47406941)), function-provider: org.camunda.bpm.dmn.feel.impl.scala.function.CustomFunctionTransformer@526b2f3e, clock: SystemClock, configuration: Configuration(false)]
2021-08-31 14:51:09.356  INFO 73769 --- [           main] o.s.c.f.web.mvc.FunctionHandlerMapping   : FunctionCatalog: org.springframework.cloud.function.context.catalog.BeanFactoryAwareFunctionRegistry@1f15e689
Aug 31, 2021 2:51:09 PM org.apache.coyote.AbstractProtocol start
INFO: Starting ProtocolHandler ["http-nio-8080"]
2021-08-31 14:51:09.393  INFO 73769 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-08-31 14:51:09.401  INFO 73769 --- [           main] com.demo.function.FunctionApplication    : Started FunctionApplication in 6.727 seconds (JVM running for 7.089)
```

### Consuming a decision

```zsh
❯ curl localhost:8080/voucherToApply -H "Content-Type: text/plain" -d "2021-08-30T20:09:17Z|47541920|PedidosYa*"
❯ PedidosYa_50_off
```

```zsh
❯ curl localhost:8080/voucherToApply -H "Content-Type: text/plain" -d "2021-01-20T20:09:17Z|47541920|PedidosYa*"
❯ PedidosYa_30_off
```

### Reference Documentation
For further reference, please consider the following sections:


* [DMN](https://en.wikipedia.org/wiki/Decision_Model_and_Notation)
* [DMN Camunda Engine](https://camunda.com/dmn/)
* [DaaS example](https://github.com/menski/dish-decision-as-a-service)
* [Camunda DMN example](https://github.com/bobbyz-dk/spring-boot-camunda-dmn-example)
* [Function](https://cloud.spring.io/spring-cloud-function/)

