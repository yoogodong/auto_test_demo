package ut.basic;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StartTest {

    @Test
    public void name() {
        //given
        ArrayList<String> list = new ArrayList<>();
        list.add("1");

        String s = list.get(0);//when

        assertEquals("2", s);//then
    }

    @Test
    public void bb() {
        //given
        ArrayList<String> list = new ArrayList<>();
        list.add("1");

        String s = list.get(0);//when

        assertEquals("1", s);//then
    }
}
