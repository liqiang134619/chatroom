package com.example.chatroom.controller;

import com.example.chatroom.domain.User;
import com.example.chatroom.service.UserService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Liq
 * @date 2019/5/10
 * 用户登录
 */
@Controller()
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;


    @RequestMapping("/login")
    public String login(User user, Model model) {



        // 调用service验证用户名和密码
        boolean success = userService.login(user);

        // 用户存在
        if (success) {
            model.addAttribute("user",user.getUsername());
            return "chat";
        }

        return "fail.html";

    }




}
