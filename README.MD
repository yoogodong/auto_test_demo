# 内容：
- 单元测试，三个实例：
  - UT 的基础结构
  - 测试用例的设计
  - 使用 mock 
- Rest API 测试，三种选择：
  - MockMvc, spring 提供, 测试脚本与被测同一进程
  - RestAssured， 社区流行， 测试脚本是独立的进程
  - RestAssuredMockMvc， RestAssured 结合spring 的 MockMvc，也就是可以做到同一进程

#运行
因为单元测试和接口测试分别采用 Test 和 IT 作为后缀，通过指定后缀来运行某种测试：
` mvn test -Dtest=*Test,*IT`  运行单元测试和接口测试

# change log
- 删除了纯粹使用 RestAssured 的示例，仅仅保留 RestAssuredMockMvc 的示例；
- 删除 testng 以及 failsafe plugin 



# 测试自动化的FAQ：
> 单元测试的价值
- 提升对方法的信心
- 将设计要求通过UT锚定，以免被将来的变更破坏
- 提升代码的设计能力，易于测试是好代码的核心要求
- 

> 单元测试的技术栈包含哪些工具或库？
- 测试框架：junit 
- 断言库: AssertJ
- 测试替身： Mockito 
- 以上三者都有竟品，但是这三个最流行
- 项目如果使用了 spring-boot, 则应添加依赖 spring-boot-starter-test 以满足以上所有依赖


> 是否应该为所有的方法写单元测试？
- 不是的，看价值，也就是是否有充分的信心
- 对于没有任何逻辑分支的方法不需要写UT

> 是否应该测试私有方法？
- 通常不应该为私有方法写测试
- 如果私有方法很复杂，可以考虑专门为私有方法写测试，这样可以降低测试用例的复杂性
- 因为私有方法不可见，所以为了能测试，需要提升方法的可见性，比如变成 protected 
