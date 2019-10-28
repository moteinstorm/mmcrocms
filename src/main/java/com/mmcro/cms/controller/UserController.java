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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.mmcro.cms.comon.ConstClass;
import com.mmcro.cms.entity.Article;
import com.mmcro.cms.entity.User;
import com.mmcro.cms.service.ArticleService;
import com.mmcro.cms.service.UserService;
import com.mmcro.cms.web.PageUtils;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@Autowired
	ArticleService articleService;
	
	
	
	
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
	
	
	/**
	 * 进入个人中心(普通注册用户)
	 * @param request
	 * @return
	 */
	@RequestMapping("home")
	public String home(HttpServletRequest request) {
		return "my/home";
	}
	
	
	/**
	 * 删除用户自己的文章
	 * @param id 文章id
	 * @return
	 */
	@RequestMapping("delArticle")
	@ResponseBody
	public boolean delArticle(HttpServletRequest request,Integer id) {
		
		//判断文章是否存在
		Article article = articleService.findById(id);
		if(article==null)
			return false;
		
		//判断文章是否属于自己的
		User loginUser =(User) request.getSession().getAttribute(
				ConstClass.SESSION_USER_KEY);
		if(loginUser.getId()!= article.getUserId()) {
			return false;
		}
		//删除文章
		return articleService.remove(id)>0;
	}
	
	/**
	 * 进入个人中心 获取我的文章
	 * @param request
	 * @return
	 */
	@RequestMapping("myarticlelist")
	public String myarticles(HttpServletRequest request,
			@RequestParam(defaultValue="1") Integer page) {
		// 获取当前用户信息
		User loginUser =(User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		//获取一页文章
		PageInfo<Article>  pageArticles = articleService.listArticleByUserId(loginUser.getId(),page);
		//利用工具类生成页码信息
		PageUtils.page(request, "/user/myarticlelist", 10, 
				pageArticles.getList(), (long)pageArticles.getSize(),
				pageArticles.getPageNum());
		request.setAttribute("pageArticles", pageArticles);
		return "/my/list";
	}
	
	
	

}
