package cn.gzsxt.sso.service;

import javax.servlet.http.HttpServletResponse;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.manager.pojo.User;

public interface UserService {
	
	/**
	 * 校验数据是否可用
	 * @param param
	 * @param type
	 * @return
	 */
	public EgoResult check(String param,Integer type);
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public EgoResult register(User user);
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public EgoResult login(String username,String password,HttpServletResponse response);
	
	
	/**
	 * 通过Token 获取信息
	 * @param token
	 * @return
	 */
	public EgoResult getUserByToken(String token);
	
	
	/**
	 * 退出
	 * @param token
	 * @return
	 */
	public EgoResult logout(String token);
	
}
