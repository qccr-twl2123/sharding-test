package com.qccr.dinner.controller.dinner;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qccr.dinner.biz.service.booking.UserService;
import com.qccr.dinner.controller.base.BaseController;
import com.qccr.dinner.model.student.UserDO;

/**
 * 
 * @author xierongli
 * @version $Id: ShardingController.java, v 0.1 2016年7月25日 下午2:43:26 xierongli Exp $
 */
@Controller
@RequestMapping("/sharding")
public class ShardingController extends BaseController{

    @Resource
    private UserService userService;
    
    
    @RequestMapping(value = "addUser")
    public String insertUser(){
        UserDO u = new UserDO();
        u.setUserId(12);
        u.setAge(25);
        u.setName("github");
        userService.insert(u);
        return "/notify/success";
    }
    
    
    @RequestMapping(value = "findAll")
    public void testFindAll(){
        List<UserDO> users = userService.findAll();
        if(null != users && !users.isEmpty()){
            for(UserDO u :users){
                System.out.println(u);
            }
        }
    }
    
    
}
