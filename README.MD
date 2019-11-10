#目的
单元测试 get started 
API 测试


#运行
`mvn package` 会运行单元测试和接口测试
`mvn package   -DskipUT -DskipIT`  跳过单元测试和接口测试


#变更
- 因为 junit4  不能很方便的支持，所以对集成测试 选择 testNG
