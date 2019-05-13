package com.example.chatroom.dao;

import com.example.chatroom.domain.Record;
import org.apache.ibatis.annotations.Mapper;

/**
 * 保存聊天记录的类
 * @author Liq
 * @date 2019/5/10
 *
 */
@Mapper
public interface IChattingRecordMapper {

    /**
     * 插入聊天记录
     * @param record 聊天记录
     */
    void insertChatting(Record record);
}
