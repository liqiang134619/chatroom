package com.example.chatroom.service;

import com.example.chatroom.controller.WebSocketServer;
import com.example.chatroom.dao.IUserMapper;
import com.example.chatroom.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: Liq
 * @Date: 2019/5/10
 * 用户service类
 */
@Service
public class UserService {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IUserMapper iUserMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);



    public boolean login(User user) {

        // 先从redis中获取数据,redis中不存在数据,再从数据库中查询数据
        Map<Object, Object> resultMap = redisTemplate.opsForHash().entries("users");

        // 查询redis
        if (!resultMap.isEmpty()) {
            for (Entry<Object, Object> map : resultMap.entrySet()) {
                // 用户名
                Object username = map.getKey();
                // 密码
                Object password = map.getValue();

                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    LOGGER.info("【==> 从redis中获取用户数据】user:{}",user);
                    return true;
                }

            }
        }

        // 查询数据库
        User resultUser = iUserMapper.findUser(user);
        Optional<User> optionalUser = Optional.ofNullable(resultUser);
        if (optionalUser.isPresent()) {
            // 查询到的用户保存到redis中
            Map<String,String>map = new HashMap<String,String>();
            map.put(resultUser.getUsername(),resultUser.getPassword());
            redisTemplate.opsForHash().putAll("users",map);
            LOGGER.info("【==> 从数据库中获取用户数据，并保存到redis中】user: {}", user);
            return true;
        }


        // 查询不到返回false
        return false;

    }
}
