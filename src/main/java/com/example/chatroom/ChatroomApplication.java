package com.example.chatroom;

import org.apache.ibatis.javassist.ClassPath;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.example.chatroom.dao")
public class ChatroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatroomApplication.class, args);
    }

}
