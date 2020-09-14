package com.melody.opensource.springboottestdemo.spring.boot.web.mvc;

import com.melody.opensource.springboottestdemo.configuration.PersonConfiguration;
import com.melody.opensource.springboottestdemo.controller.PersonController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author zqhuangc
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
@Import(PersonConfiguration.class)
public class PersonControllerSpringBootTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void testPerson() throws Exception {

        //mvc.perform(get("/").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
        mvc.perform(get("/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

}
