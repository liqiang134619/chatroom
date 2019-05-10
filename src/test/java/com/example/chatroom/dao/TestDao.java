package com.example.chatroom.dao;

import com.example.chatroom.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Liq
 * @Date: 2019/5/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDao {

    @Autowired
    IUserMapper iUserMapper;

    @Test
    public void test1() {

        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        User user1 = iUserMapper.findUser(user);
        System.out.println(user1);
    }

}
