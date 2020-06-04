package com.hh.controller;

import com.hh.enums.ResponseStatus;
import com.hh.pojo.Admin;
import com.hh.pojo.Message;
import com.hh.pojo.UserDetails;
import com.hh.service.AdminService;
import com.hh.service.MessageService;
import com.hh.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import com.hh.util.CodeUtil;

@RestController
@RequestMapping("api/message/")
public class MessageController extends BaseController {
    private MessageService messageService;
    private AdminService adminService;
    private Random random=new Random();

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("list")
    public Object list(HttpServletRequest request){
        UserDetails userDetails=getUserDetails(request);
        if (userDetails==null){
            return ResultUtil.fail(ResponseStatus.NO_LOGIN);
        }
        return ResultUtil.ok(messageService.getList(userDetails.getUserId()));
    }

    @PostMapping("send")
    public Object send(@RequestBody Message message, HttpServletRequest request){
        UserDetails userDetails=getUserDetails(request);
        if (userDetails==null){
            return ResultUtil.fail(ResponseStatus.NO_LOGIN);
        }
        List<Message> messages=messageService.getList(userDetails.getUserId());
        int sellId;
        if (messages.size()==0){
            List<Admin> admins=adminService.getAdmins();
            int index=random.nextInt(admins.size());
            sellId=admins.get(index).getAdminId();
        }else {
            sellId=messages.get(0).getSellId();
        }
        message.setSellId(sellId);
        message.setBuyId(userDetails.getUserId());
        message.setSend(1);
        message.setTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(message);
        int res=messageService.sendMessage(message);
        if (res==1){
            return ResultUtil.ok();
        }
        return ResultUtil.error();
    }
}
