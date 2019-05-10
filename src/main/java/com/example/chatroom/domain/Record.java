package com.example.chatroom.domain;

import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * @author Liq
 * @date 2019/5/10
 */
@Data
@ToString
public class Record {

    private int id;
    private String sender;
    private String message;
    private Date date;

}
