package com.azito.azito.Controllers;

import com.azito.azito.Models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mocMvc;
    @Autowired
    private UserController userController;
    @Test
    void test() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void test1() throws Exception{
        this.mocMvc.perform(get("/")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void loginTest() throws Exception {
        this.mocMvc.perform(get("/profile"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test void correctLogin() throws Exception {
        this.mocMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("1@mail.ru").password("1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test void unCorrectLogin() throws Exception {
        this.mocMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("1@mail.ru").password("5"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test void  mainPageTest() throws Exception {
        this.mocMvc.perform(get("/"))
                .andDo(print())
                .andExpect(xpath("/html/body/nav/div/span/a").string("Profile"));
    }



}