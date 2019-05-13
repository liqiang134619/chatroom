package com.example.chatroom.dao;

import com.example.chatroom.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * 用户信息数据库操作的类
 * @author Liq
 * @date 2019/5/10
 *
 */
@Mapper
public interface IUserMapper {


    /**
     * 查询用户对象
     * @param user 查询对象
     * @return result 返回null查询不到
     */
    User findUser(User user);
}
