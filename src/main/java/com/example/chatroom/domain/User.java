package com.example.chatroom.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: Liq
 * @Date: 2019/5/10
 * 用户实体类
 */
@Data
@ToString
public class User {
    private String username;
    private String password;

}
