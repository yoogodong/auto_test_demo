package ut.first;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccumulatorTest {

    private Accumulator accumulator;

    @Before
    public void setUp() throws Exception {
        accumulator = new Accumulator();
    }

    @Test(expected = NumberOutOfBoundException.class)
    public void accumulate_should_throw_when_input_is_1001() throws NumberOutOfBoundException {
        accumulator.accumulate(1001);
    }

    @Test(expected = NumberOutOfBoundException.class)
    public void accumulate_should_throw_when_input_is_negative1() throws NumberOutOfBoundException {
        accumulator.accumulate(-1);
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

}