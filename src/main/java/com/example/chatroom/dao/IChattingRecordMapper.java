package com.example.chatroom.dao;

import com.example.chatroom.domain.Record;
import org.springframework.stereotype.Component;

/**
 * @author Liq
 * @date 2019/5/10
 * 保存聊天记录的类
 */
@Component
public interface IChattingRecordMapper {

    /**
     * 插入聊天记录
     * @param record
     */
    void InsertChatting(Record record);
}
