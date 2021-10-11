package ut.third.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Mock 意思是模拟，可以让指定的类或接口的方法按要求响应
 * 本示例包括：
 * 1，mock
 * 2，spy
 * 3, 参数匹配
 * 3, 验证（verify)
 * 4，次数验证
 * 5，顺序验证
 */
public class ListTest {

    @Test
    public void mock_some_interface() {
       //建立 mock 对象, 注意 mock 方法是静态导入的
        List mockedList = mock(List.class);

        System.out.println("mockedList 长度 = " + mockedList.size());

        //指定 mock 对象某个方法按要求返回， 术语叫做"打桩（stubbing）"， 比如下面叫做打桩size（）方法
        when(mockedList.size()).thenReturn(1000);

        //检验结果
        System.out.println("mockedList 长度 = " + mockedList.size());
    }

    /**
     * 也可以使用具体的类建立 mock 对象
     */
    @Test
    void mock_some_class() {
        List mockedList = mock(ArrayList.class);

        when(mockedList.size()).thenReturn(1000);

        System.out.println(mockedList.size());
    }

    /**
     * 只 mock 打桩的方法就做 spy，也就是部分 mock
     */
    @Test
    void spy_some_object() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("a");


        ArrayList spyList = spy(arrayList);

        //spy 对象的打桩与之前的形式不同，猜猜是为什么
        doReturn(100).when(spyList).size();

        System.out.println("spyList.size() = " + spyList.size());
        System.out.println("spyList.get(0) = " + spyList.get(0));
    }

    /**
     * 打桩时参数也要匹配
     */
    @Test
    void param_match() {
        List mockedList = mock(List.class);

        when(mockedList.get(1)).thenReturn("1111");
        when(mockedList.get(9)).thenReturn("9999");

        System.out.println("mockedList.get(1) = " + mockedList.get(1));
        System.out.println("mockedList.get(9) = " + mockedList.get(9));

//      参数匹配器包含 any*(), any(), eq(), argThat() 请参考 API 文档
        when(mockedList.get(anyInt())).thenReturn("always");
        System.out.println("mockedList.get(1) = " + mockedList.get(1));
        System.out.println("mockedList.get(22) = " + mockedList.get(22));

//        只要有任何一个参数使用了匹配器，其他的参数也要使用匹配器, 下面是错误的
//        when(mockedList.subList(10,anyInt())).thenReturn(Arrays.asList("a","b"));
        when(mockedList.subList(eq(10),anyInt())).thenReturn(Arrays.asList("a","b"));
        System.out.println("mockedList.subList(10,10) = " + mockedList.subList(10,10));
    }

    /**
     * 验证（verify) 是为了确认 mock 对象的指定方法是否被调用、调用的次数和顺序
     */
    @Test
    void verify_some_method_was_called() {
        List mockedList = mock(List.class);

        mockedList.add("a");
        mockedList.size();

        verify(mockedList).add("a");
//        verify(mockedList).add("b"); //这个无法通过验证；

//        在验证时经常使用参数匹配器
        verify(mockedList).add(anyString());

        verify(mockedList).size();
    }


    /**
     * 验证 mock 对象方法被调用的次数
     */
    @Test
    void verify_some_method_was_called_specific_time() {
        List mockedList = mock(List.class);

        mockedList.add("a");
        mockedList.add(9);
        mockedList.size();

        verify(mockedList, times(2)).add(any());
        verify(mockedList,atLeast(1)).size();
        verify(mockedList,atMostOnce()).size();
    }

    /**
     * 验证 mock 对象方法被调用的顺序
     */
    @Test
    void verify_some_method_was_called_inOrder() {
        List mockedList = mock(List.class);

        mockedList.add("a");
        mockedList.size();


        InOrder inOrder = inOrder(mockedList);
        //以下两行顺序敏感
        inOrder.verify(mockedList).add(any());
        inOrder.verify(mockedList).size();
    }
}
