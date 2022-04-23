package com.barber.controller;

import com.barber.common.RetVal;
import org.springframework.web.bind.annotation.*;

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
    public RetVal login(){
        return RetVal.success().data("token","admin");
    }
    @GetMapping("info")
    public RetVal info(){
        return RetVal.success()
                .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
