package com.example.chatroom.domain;

import lombok.Data;
import lombok.ToString;

/**
 * 用户实体类
 * @author  Liq
 * @date 2019/5/10
 *
 */
@Data
@ToString
public class User {

    private String username;
    private String password;

}
