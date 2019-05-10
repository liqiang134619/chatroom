package com.example.chatroom;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Liq
 * @date 2019/5/10
 * 测试时间
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDate {

    @Test
    public void test() {
        long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(l);
    }


}
