package com.example.chatroom.domain;

import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * 聊天纪录实体类
 * @author Liq
 * @date 2019/5/10
 *
 */
@Data
@ToString
public class Record {

    private String id;
    private String sender;
    private String message;
    private Date date;

}
