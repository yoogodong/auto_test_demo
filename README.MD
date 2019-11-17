# 演示内容：
- 单元测试，两个示例：使用 mock 和 不使用 mock 
- Rest API 测试： 使用 MockMvc 和 RestAssuredMockMvc 两种方式
- UI 测试： 


#运行
因为单元测试和接口测试分别采用 Test 和 IT 作为后缀，所以：
` mvn test -Dtest=*Test,*IT`  运行单元测试和接口测试
` mvn test -Dtest=*Test` 只运行单元测试
` mvn test -Dtest=*IT` 只运行接口测试

# change log
- 删除了纯粹使用 RestAssured 的示例，仅仅保留 RestAssuredMockMvc 的示例；
- 删除 testng 以及 failsafe plugin 

