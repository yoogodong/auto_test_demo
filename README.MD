#目的
单元测试 get started 
API 测试

#运行
`mvn test` 运行单元测试

`mvn verify` 运行单元测试和集成测试, 集成测试前后会start/stop spring-boot 

`mvn verify   -DskipUT`  跳过单元测试运行集成测试


集成测试也可以在 IDE 中选择指定的运行，这也是 *IT 都继承自 PreIT 的原因。 换句话说， 仅仅通过 maven 命令运行没有必要继承这个类



#变更
- 因为 junit4  不能很方便的支持集成测试 所以选择 testNG