package com.mmcro.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mmcro.cms.comon.ConstClass;
import com.mmcro.cms.entity.User;
import com.mmcro.cms.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	
	
    //@PostMapping// 只能接受post方法的请求
	//@RequestMapping(value = "register",method=RequestMethod.GET)
	@GetMapping("register")  // 只接受get的请求
	public String register() {
		return "user/register";
	}
	
	@RequestMapping("index")
	public String index() {
		return "user/index";
	}
	
	/**
	 * 判断用户名是否已经被占用
	 * @param username
	 * @return
	 */
	@RequestMapping("checkExist")
	@ResponseBody
	public boolean checkExist(String username) {
		return !userService.checkUserExist(username);
	}
	
	@PostMapping("register")  // 只接受POst的请求\
	public String register(HttpServletRequest request,
			@Validated User user,
			BindingResult errorResult) {
		if(errorResult.hasErrors()) {
			return "user/register";
		}
		
		int result = userService.register(user);
		if(result>0) {
			return "redirect:login";
		}else {
			request.setAttribute("errorMsg", "系统错误，请稍后重试");
			return "user/register";
		}
		
		
		
	}
	
	
	@RequestMapping(value = "login",method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstClass.SESSION_USER_KEY);
		return "user/login";
	}
	
	@PostMapping("login")  // 只接受POst的请求
	public String login(HttpServletRequest request,
			@Validated User user,
			BindingResult errorResult) {
		
		if(errorResult.hasErrors()) {
			return "login";
		}
		
		//登录
		User loginUser = userService.login(user);
		if(loginUser==null) {
			request.setAttribute("errorMsg", "用户名密码错误");
			return "user/login";
		}else {
			//用户信息保存在session当中
			request.getSession().setAttribute(ConstClass.SESSION_USER_KEY, loginUser);
			//普通注册用户
			if(loginUser.getRole()==ConstClass.USER_ROLE_GENERAL) {
				return "redirect:home";	
			//管理员用户	
			}else if(loginUser.getRole()==ConstClass.USER_ROLE_ADMIN){
				return "redirect:../admin/index";	
			}else {
				// 其他情况
				return "user/login";
			}
		}
		
	}
	
	
	

}
