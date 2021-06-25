package com.learn.interview.controller;


import com.learn.interview.service.UserService;

/**
 * @ClassName spring
 * @Description 描述
 * @Date 2021/3/29 5:28 下午
 * @Author lin
 * @Version 1.0
 */
public class UserController {

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
