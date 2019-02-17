package com.example.demo.controller;

import com.example.demo.service.ShareService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share")
public class ShareController {
    @Autowired
    ShareService shareService;

    @PatchMapping("/getshare")
    public Object addComment(@Param("page")int page,@Param("country")String country){
        try{
                return shareService.getshare(page, country);
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
