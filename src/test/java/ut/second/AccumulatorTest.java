package ut.second;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * 1.测试类和被测类共享包名
 */
public class AccumulatorTest {

    private Accumulator accumulator;

    @BeforeEach
    public void setUp() throws Exception {
        accumulator = new Accumulator();
    }

    @Test
    public void accumulate_should_return_0_when_input_0() throws NumberOutOfBoundException {
        int result = accumulator.accumulate(0);
        assertEquals(0,result);
    }

    @Test
    public void accumulate_should_return_50050_when_input_1000() throws NumberOutOfBoundException {
        int result = accumulator.accumulate(1000);
        assertEquals(500500,result);
    }

    @Test
    public void accumulate_should_throw_when_input_is_1001() throws NumberOutOfBoundException {
        Assertions.assertThrows(NumberOutOfBoundException.class,()->{
            accumulator.accumulate(1001);
        });
    }

    @Test
    public void accumulate_should_throw_when_input_is_negative1() throws NumberOutOfBoundException {
        Assertions.assertThrows(NumberOutOfBoundException.class,()->{
            accumulator.accumulate(-1);
        });
    }

}