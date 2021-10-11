package ut1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 1，测试类名 = 被测类 + Test
 * 2，测试方法命名要表明测试目的， 可以有任意个测试方法，但要围绕一个被测类
 * 3，每个测试都至少包含一个 given-when-then 结构
 * 4, given 是给定条件，when 是被测试目标，then 是结果断言
 * 5，测试框架junit,会调用所有的 @Test 标注的方法， 不同的测试方法没有确定的执行顺序
 * 6，常用的标注，有 @Test、@BeforeEach、 @AfterEach @DisplayName
 * 7, 常用的断言方法，还有 assertTrue、assertFalse、assertThat
 * 8, 需要多个断言的时候，考虑是否可以使用 assertThat, assertThat 可以简化断言的表达 @Todo
 * 9，运行测试，可以在 IDE 中，也可以通过 maven 命令（mvn test）
 * 10, 这里的必要依赖是 junit5, 这里是通过引用 spring-boot-starter-test 来包含这个依赖的
 */
public class StringBuilderTest {

    @Test
    void testAppend() {
        StringBuilder builder = new StringBuilder();

        assertEquals("",builder.toString(),"初始化错误");

        builder.append("a");
        assertEquals("a",builder.toString(),"转化字符串错误");

        builder.append(1);
        assertEquals("a1",builder.toString(),"转化字符串错误");
    }

    @Test
    void testLength() {
        StringBuilder builder = new StringBuilder();

        assertEquals(0,builder.length(),"初始化长度不对");

        builder.append(1);
        assertEquals(1,builder.length(),"长度不对");

        builder.append(2345);
        assertEquals(5,builder.length(),"长度不对");
    }
}
