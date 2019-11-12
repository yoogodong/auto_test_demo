# 内容：
- 单元测试， 采用了 mockito 作为mock 工具
- API 测试：演示了API 测试的三种方式: RestAssured, MockMvc 和 RestAssuredMvc

#运行
`mvn test` 运行单元测试

`mvn verify` 运行单元测试和集成测试, 集成测试前后会start/stop spring-boot 

`mvn verify   -DskipUT`  跳过单元测试运行集成测试


集成测试也可以在 IDE 中选择指定的运行，这也是 *IT 都继承自 PreIT 的原因。 换句话说， 仅仅通过 maven 命令运行没有必要继承这个类

