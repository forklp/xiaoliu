package com.example.demo.controller;

import com.example.demo.entity.TodPosition;
import com.example.demo.entity.TomPosition;
import com.example.demo.repository.TodPositionRepository;
import com.example.demo.repository.TomPositionRepository;
import com.example.demo.util.RandomGenerator;
import com.example.demo.util.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    VerificationCode verificationCode;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TomPositionRepository tomPositionRepository;

    @Autowired
    TodPositionRepository todPositionRepository;

    @PostMapping("/reservation")
    public Object reservation(@RequestParam("id")Long id,
                              @RequestParam("time")String time,
                              @RequestParam("phone")String phone,
                              @RequestParam("name")String name,
                              @RequestParam("code")String code){
       if(!redisTemplate.opsForValue().get(phone+'v').equals(code))return "验证码错误";
       if(tomPositionRepository.findByUser1PhoneOrUser2PhoneOrUser3Phone(phone,phone,phone)!=null)return "您已经预约座位";
       TomPosition tomPosition = tomPositionRepository.getOne(id);
       String tem = "";
       int num = time.length();
       for(int i = 0;i<time.length();i++){
           switch (time.charAt(i)){
               case '0':
                   if(tomPosition.isMorning()){
		    num--;
                    tem += " 该位置上午已被预约";
                    break;
                   }
                   tomPosition.setMorning(true);
                   tomPosition.setUser1Name(name);
                   tomPosition.setUser1Phone(phone);
                   break;
               case '1':
                    if(tomPosition.isAfternoon()){
		    num--;	
                    tem += " 该位置下午已被预约";
                    break;
                    }
                   tomPosition.setAfternoon(true);
                   tomPosition.setUser2Name(name);
                   tomPosition.setUser2Phone(phone);
                   break;
               case '2':
                    if(tomPosition.isNight()){
		    num--;
                    tem += " 该位置晚上已被预约";
                    break;
                    }
                   tomPosition.setUser3Name(name);
                   tomPosition.setUser3Phone(phone);
                   tomPosition.setNight(true);
                   break;
           }
       }
       tomPositionRepository.save(tomPosition);
       if(tem.equals(""))return "预约成功";
       if(num == 0)return "预约失败,您选的时间段都无空闲";
       return "预约成功"+'('+tem+')';
    }

    @PostMapping("/getcode")
    public Object getCode(@RequestParam("phone")String phone){
        String code = RandomGenerator.generateCode();
        verificationCode.getCode(phone,code);
        redisTemplate.opsForValue().set(phone+"v",code,604800, TimeUnit.SECONDS);
        return "发送验证码成功";
    }

    @PostMapping("/getallre")
    public Object getallre(){
        Sort sort = new Sort(Sort.Direction.ASC, "positionId");
        return tomPositionRepository.findAll(sort);
    }

    @PostMapping("/getall")
    public Object getall(){
        Sort sort = new Sort(Sort.Direction.ASC, "positionId");
        return todPositionRepository.findAll(sort);
    }

    @PostMapping("/getreuserinfo")//获得用户预约的座位信息
    public Object getReUserInfo(@RequestParam("phone")String phone){
        return tomPositionRepository.findByUser1PhoneOrUser2PhoneOrUser3Phone(phone,phone,phone);
    }
    @PostMapping("/getuserinfo")//获得用户当天的座位信息
    public Object getuserinfo(@RequestParam("phone")String phone){
        return todPositionRepository.findByUser1PhoneOrUser2PhoneOrUser3Phone(phone,phone,phone);
    }

    @PostMapping("/deletere")
    public Object deletere(@RequestParam("phone")String phone){
        tomPositionRepository.deleteUser1Reservation(phone);
        tomPositionRepository.deleteUser2Reservation(phone);
        tomPositionRepository.deleteUser3Reservation(phone);
        return "删除预约成功";
    }
}
