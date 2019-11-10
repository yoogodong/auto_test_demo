package ut.mock;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//演示最基本的 mock
public class ListTest {

    @Test
    public void showListMock() {
        List list = mock(List.class);
        when(list.size()).thenReturn(1000);

        System.out.println(list.size());
    }
}
