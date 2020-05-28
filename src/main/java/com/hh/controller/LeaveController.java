package com.hh.controller;


import com.hh.pojo.Leave;
import com.hh.pojo.UserDetails;
import com.hh.service.LeaveService;
import com.hh.util.ResponseStatus;
import com.hh.util.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/leave")
public class LeaveController extends BaseController {
    private LeaveService leaveService;

    @PostMapping("/addLeave")
    public Object addLeave(@RequestBody Leave leave, HttpServletRequest request){
        try {
            UserDetails userDetails = getUserDetails(request);
            if (userDetails == null) {
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            }
            int userId = userDetails.getUserId();
            leave.setUserId(userId);

            if(leaveService.addLeave(leave) == 1)
                return ResultUtil.ok();
            else
                return ResultUtil.fail(ResponseStatus.PARAM_ERROR);

        }catch (Exception e){
            return ResultUtil.error();
        }
    }

    @PostMapping("/updateLeave")
    public Object updateLeave(@RequestBody Leave leave, HttpServletRequest request){
        try {
            UserDetails userDetails = getUserDetails(request);
            if (userDetails == null) {
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            }
            int userId = userDetails.getUserId();
            leave.setUserId(userId);

            //todo:判断是否是这个人的留言
            if(leaveService.haveLeave(userId,leave.getLeaveId())){
                if(leaveService.updateLeave(leave) == 1)
                    return ResultUtil.ok();
                else
                    return ResultUtil.fail(ResponseStatus.PARAM_ERROR);
            }
            else
                return ResultUtil.fail(ResponseStatus.FORBIDDEN);

        }catch (Exception e){
            return ResultUtil.error();
        }
    }

    @PostMapping("/deleteLeave")
    public Object deleteLeave(@RequestBody Leave leave,HttpServletRequest request){
        try {
            UserDetails userDetails = getUserDetails(request);
            if (userDetails == null) {
                return ResultUtil.fail(ResponseStatus.NO_LOGIN);
            }
            int userId = userDetails.getUserId();
            leave.setUserId(userId);

            //todo:判断是否是这个人的留言
            if(leaveService.haveLeave(userId,leave.getLeaveId())){
                if(leaveService.deleteLeave(leave.getLeaveId()) == 1)
                    return ResultUtil.ok();
                else
                    return ResultUtil.fail(ResponseStatus.PARAM_ERROR);
            }
            else
                return ResultUtil.fail(ResponseStatus.FORBIDDEN);

        }catch (Exception e){
            return ResultUtil.error();
        }
    }
}
