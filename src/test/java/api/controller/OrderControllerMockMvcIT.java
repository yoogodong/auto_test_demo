package api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * 演示使用 Spring MockMvc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderControllerMockMvcIT {
    @Autowired
    MockMvc mockMvc;

    /**
     * API 结构：mockMvc.perform(x).andExpect(y)
     * x 用于构造请求, 使用 MockMvcRequestBuilder
     * y 用于验证应答, 使用 MockMvcResultMatcher
     */
    @Test
    public void when_input_1() throws Exception {


        mockMvc.perform(get("/orders/1").accept(APPLICATION_JSON))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalPrice").value(3250))
                .andExpect(jsonPath("orderItemList[0].quantity").value(2));
    }

    @Test
    public void when_input_5() throws Exception {

        mockMvc.perform(get("/orders/5").accept("application/json;charset=utf-8"))
                .andExpect(status().is(404))
                .andExpect(jsonPath("[0]").value("对象不存在")).andDo(print());
    }
}