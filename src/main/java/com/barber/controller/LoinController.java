package com.barber.controller;

import com.barber.common.Result;
import com.barber.common.RetVal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author will
 * 登录接口
 * 解决跨域请求:@CrossOrigin
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoinController {
    @PostMapping("login")
    public Result<String> login(){
        return Result.data("token","admin");
    }
    @GetMapping("info")
    public Result<Map<String,String>> info(){
        HashMap<String, String> hashMap = new HashMap<>(3);
        hashMap.put("roles","[admin]");
        hashMap.put("name","admin");
        hashMap.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.data(hashMap);
    }
}
