package com.wyl.service;

import com.wyl.ApplicationServiceBoot;
import com.wyl.controller.ExampleController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wangyulin on 27/02/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationServiceBoot.class)
public class ExampleServiceTest {

    @Autowired
    private ExampleService exampleService;

    private TestRestTemplate template = new TestRestTemplate();

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(new ExampleController()).build();
    }

    @Test
    public void testExampleServiceTest() {
        String name = exampleService.findName("1");
        System.out.println("id = 1 : " + name);
    }

    @Test
    public void testAPI() throws Exception {
        //String result = template.getForObject("http://127.0.0.1:8080/name/{id}", String.class, "2");
        //System.out.println(result);

        RequestBuilder request = null;
        request = MockMvcRequestBuilders.get("http://127.0.0.1:8080/name/{id}", "1");
        ResultActions result = mvc.perform(request);
        System.out.println(result);
                //.andExpect(status().isOk())
                //.andExpect(content().string("Wangyulin"));
    }

}
