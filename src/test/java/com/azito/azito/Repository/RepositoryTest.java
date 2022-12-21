package com.azito.azito.Repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/set-data-for-test-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/set-data-for-test-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepository productRepository;
    @Test
    public void getUserByEmail(){
        Assert.assertTrue(userRepository.findByEmail("1@mail.ru").getId() == 1L);
        System.out.println("Test 1: Sucsesfull");

    }

    @Test
    public void getPr(){
        System.out.println("Test 2");
    }
}
