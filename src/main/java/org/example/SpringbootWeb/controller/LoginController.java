package org.example.SpringbootWeb.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.SpringbootWeb.pojo.Emp;
import org.example.SpringbootWeb.pojo.Result;
import org.example.SpringbootWeb.service.EmpService;
import org.example.SpringbootWeb.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    public EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        log.info("请求登陆: {},{}", emp.getUsername(), emp.getPassword());
        Emp empCheck = empService.getLoginInfo(emp);

        //登录成功，生成令牌并下发
        if (empCheck != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", empCheck.getId());
            claims.put("name", empCheck.getName());
            claims.put("username", empCheck.getUsername());

            String jwt =  JwtUtils.createJWT(claims);
            return Result.success(jwt);
        }
        //登录失败，返回错误信息

        return Result.error("用户名或密码错误");
    }
}
