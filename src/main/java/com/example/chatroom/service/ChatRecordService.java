package com.example.chatroom.service;

import com.example.chatroom.dao.IChattingRecordMapper;
import com.example.chatroom.domain.Record;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Liq
 * @date 2019/5/10
 * 保存用户的聊天记录的类
 */

@Service
public class ChatRecordService {

    @Autowired
    IChattingRecordMapper chattingRecord;



    /**
     * 保存聊天记录到数据库中
     * @param nickName sender
     * @param message  消息内容
     */
    public void saveChatting(String nickName, String message) {

        // 获取时间
        Date date = new Date();

        Record record = new Record();
        record.setSender(nickName);
        record.setMessage(message);
        record.setDate(date);

        chattingRecord.InsertChatting(record);


    }
}
