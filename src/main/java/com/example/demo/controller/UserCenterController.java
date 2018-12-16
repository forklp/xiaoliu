package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user_center")
public class UserCenterController {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public Object userCenter(HttpServletRequest request) {
        User user = new User();

        String token = request.getHeader("token");
        Object object = redisTemplate.opsForValue().get(token);
        String account = (String) object;

        User user1 = userRepository.findByUserAccount(account);
        user.setImageUrl(user1.getImageUrl());
        user.setNickName(user1.getNickName());
        user.setUserPoint(user1.getUserPoint());
        return user;
    }

    @GetMapping("/user_info")
    public Object userInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        Object object = redisTemplate.opsForValue().get(token);
        String account = (String) object;

        User user = userRepository.findByUserAccount(account);
        user.setUserPassword("null");
    
        return user;
    }

    @PostMapping("/user_info")
    public Object postUserInfo(HttpServletRequest request, @RequestBody User user) {
        String token = request.getHeader("token");
        Object object = redisTemplate.opsForValue().get(token);
        String account = (String) object;

        User user1 = userRepository.findByUserAccount(account);
        user1.setNickName(user.getNickName());
        user1.setImageUrl(user.getImageUrl());
        userRepository.save(user1);

        return user1;
    }

    @GetMapping("/get_code")
    public Object getCode(HttpServletRequest request) {
        String token = request.getHeader("token");
        Object object = redisTemplate.opsForValue().get(token);
        String account = (String) object;

        User user = userRepository.findByUserAccount(account);
        User user1 = new User();
        user1.setUserCode(user.getUserCode());

        return user1;
    }

    @GetMapping("/logout")
    public Object logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        redisTemplate.delete(token);
        return 1;
    }
}
