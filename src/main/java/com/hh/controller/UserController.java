package com.hh.controller;


import com.alibaba.fastjson.JSONObject;
import com.hh.pojo.UserDetails;
import com.hh.service.MailService;
import com.hh.service.UserService;
import com.hh.pojo.User;
import com.hh.util.CodeUtil;
import com.hh.util.CookieUtil;
import com.hh.util.ResponseStatus;
import com.hh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{
    private UserService userService;
    private MailService mailService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 登陆
     */
    @PostMapping("login")
    public Object login(@RequestBody User user, HttpServletResponse response) {
        //登陆
        UserDetails userDetails = userService.login(user);
//        Map<String, Object> data = new HashMap<>(1);
//        data.put("token", jwtTokenUtil.generateToken(userDetails));
//        response.addCookie(new Cookie("Authorization",jwtTokenUtil.generateToken(userDetails)));
        CookieUtil.setCookie(response,"Authorization",jwtTokenUtil.generateToken(userDetails));
        return ResultUtil.ok();
    }

    @PostMapping("/getCode")
    public Object getCode(@RequestBody Map<String ,String > map, HttpSession session){
        String email=map.get("email");
        if (email==null){
            return ResultUtil.fail(ResponseStatus.PARAM_ERROR);
        }
        if (userService.exitsUser(email)){
            return ResultUtil.fail(ResponseStatus.USER_EMAIL_EXIST);
        }
        String code= CodeUtil.randomCode();
        mailService.sendSimpleMail(email,"trade-plateform验证码",code);
        session.setAttribute("code",code);
        return ResultUtil.ok();
    }

    @PostMapping("/checkCode")
    public Object checkCode(@RequestBody Map<String ,String> map,HttpSession session){
        String code=map.get("code");
        if (code==null){
            return ResultUtil.fail(ResponseStatus.PARAM_ERROR);
        }
        String rel_code= (String) session.getAttribute("code");
        if (rel_code==null){
            return ResultUtil.fail(ResponseStatus.NO_CODE);
        }
        if (!code.equals(rel_code)){
            return ResultUtil.fail(ResponseStatus.VEL_CODE_ERROR);
        }
        session.setAttribute("code","ok");
        return ResultUtil.ok();
    }

    @PostMapping("/registry")
    public Object addUser(@RequestBody User user,HttpSession session){
        if (session.getAttribute("code")==null){
            return ResultUtil.fail(ResponseStatus.NO_CODE);
        }
        if (userService.addUser(user)!=1){
            return ResultUtil.error();
        }
        return ResultUtil.ok();
    }

    @PostMapping("/updateUser")
    public Object updateUser(@RequestBody User user, HttpServletRequest request){
        UserDetails userDetails = getUserDetails(request);
        if (userDetails == null) {
            return ResultUtil.fail(ResponseStatus.NO_LOGIN);
        }
        userService.updateUser(user);
        return ResultUtil.ok();
    }
}
