package com.qccr.sharding.controller.base;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qccr.sharding.biz.service.student.UserService;
import com.qccr.sharding.model.student.UserDO;

@Controller("/sharding")
public class ShardingController  extends BaseController{
	
	@Resource
	private UserService userService;
	
	@RequestMapping("addUser")
	public String insertUser(){
		
		UserDO u = new UserDO();
		Random r = new Random();
		u.setUserId(r.nextInt(10));
		u.setAge(12);
		u.setName("jack");
		
		userService.insert(u);
		
		return "/sharding/success";
		
	}

}
