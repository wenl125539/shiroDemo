package com.wenl;

import com.wenl.Service.UserService;
import com.wenl.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    UserService service;

    @Test
    void contextLoads() {
        User wenl = service.findUserByName("wenl1");
        System.out.println(wenl.toString());
    }

}
