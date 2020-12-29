package com.njust.vuelab.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.njust.vuelab.common.lang.Result;
import com.njust.vuelab.common.lang.dto.LoginDto;
import com.njust.vuelab.common.lang.dto.RegisterDto;
import com.njust.vuelab.entity.Account;
import com.njust.vuelab.service.AccountService;
import com.njust.vuelab.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 陈浩东
 * @since 2020-12-28
 */
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto dto, HttpServletResponse response){
        Account account = accountService.getOne(new QueryWrapper<Account>().eq("user_name",dto.getUserName()));
        Assert.notNull(account,"用户不存在");
        if (!account.getPassword().equals(dto.getPassword())){
            return Result.fail("密码错误");
        }

        String jwt = jwtUtils.generateToken(account.getId());

        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return Result.success(MapUtil.builder()
                .put("id",account.getId())
                .put("userName",account.getUserName())
                .put("role",account.getRole()).map()
        );

    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);

    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto dto){
        Account temp = accountService.getOne(new QueryWrapper<Account>().eq("user_name",dto.getUserName()));
        if (dto.getId() == null){
            Assert.isNull(temp,"用户已存在");
        }

        Account newAccount = new Account();
        newAccount.setNickName(dto.getNickName());
        newAccount.setPassword(dto.getPassword());
        newAccount.setRole(dto.getRole());
        newAccount.setUserName(dto.getUserName());
        Account teacher = accountService.getOne(new QueryWrapper<Account>().eq("nick_name",dto.getTchName()));
        Assert.notNull(teacher,"老师不存在");
        newAccount.setTeacherId(teacher.getId());

        accountService.saveOrUpdate(newAccount);


        return Result.success(null);

    }

}
