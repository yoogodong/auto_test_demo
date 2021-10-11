package ut2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * 1，测试类和被测类共享包名
 * 2，一个被测方法有多个测试用例，这里将每个用例都对应了一个测试方法，这种方式更清晰，所以推荐使用
 * 3，测试方法不该捕捉任何异常，所以通常声明 throws Exception.
 * 4，使用 assertThrows 来断言应该抛出的异常
 *
 */
public class AccumulatorTest {

    private Accumulator accumulator;

    @BeforeEach
    public void setUp() throws Exception {
        accumulator = new Accumulator();
    }

    @Test
    public void testAccumulate_0() throws NumberOutOfBoundException {
        int result = accumulator.accumulate(0);
        assertEquals(0,result,"输入为0的累加结果不对");
    }

    @Test
    public void testAccumulate_1000() throws NumberOutOfBoundException {
        int result = accumulator.accumulate(1000);
        assertEquals(500500,result,"输入为1000的累加结果不对");
    }

    @Test
    public void testAccumulate_1001() throws NumberOutOfBoundException {
        Assertions.assertThrows(NumberOutOfBoundException.class,()->{
            accumulator.accumulate(1001);
        },"输入为1001的累加结果不对");
    }

    @Test
    public void testAccumulate_Minus1() throws NumberOutOfBoundException {
        Assertions.assertThrows(NumberOutOfBoundException.class,()->{
            accumulator.accumulate(-1);
        },"输入为-1的累加结果不对");
    }
}