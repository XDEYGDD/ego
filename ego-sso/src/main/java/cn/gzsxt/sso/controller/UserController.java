package cn.gzsxt.sso.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.manager.pojo.User;
import cn.gzsxt.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/showLogin")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/showRegister")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping(value="/check/{param}/{type}",method=RequestMethod.GET)
	@ResponseBody
	public Object check(@PathVariable("param") String param,@PathVariable("type") Integer type,String callback) {
		EgoResult result = userService.check(param,type);
		if( null!= callback && !"".equals(callback)) {
			return callback+"("+JsonUtils.objectToJson(result)+")";
		}
		return result;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public EgoResult register(User user) {
		EgoResult result = userService.register(user);
		return result;
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public EgoResult register(String username,String password,HttpServletResponse response) {
		EgoResult result = userService.login(username, password, response);
		return result;
	}
	
	@RequestMapping(value="/token/{token}",method=RequestMethod.GET)
	@ResponseBody
	public Object getUserByToken(@PathVariable("token") String token,String callback) {
		EgoResult result = userService.getUserByToken(token);
		if( null!= callback && !"".equals(callback)) {
			return callback+"("+JsonUtils.objectToJson(result)+")";
		}
		return result;
	}
	
	
	@RequestMapping(value="/logout/{token}",method=RequestMethod.GET)
	@ResponseBody
	public Object logout(@PathVariable("token") String token,String callback) {
		EgoResult result = userService.getUserByToken(token);
		if( null!= callback && !"".equals(callback)) {
			return callback+"("+JsonUtils.objectToJson(result)+")";
		}
		return result;
	}
	
}
