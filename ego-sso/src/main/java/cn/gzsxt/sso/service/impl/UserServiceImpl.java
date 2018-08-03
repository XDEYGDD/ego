package cn.gzsxt.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.pojo.EgoResult;
import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.manager.mapper.UserMapper;
import cn.gzsxt.manager.pojo.User;
import cn.gzsxt.manager.pojo.UserExample;
import cn.gzsxt.manager.pojo.UserExample.Criteria;
import cn.gzsxt.sso.service.UserService;
import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JedisCluster jedisCluster;


	
	@Value("${EGO_USER_REDIS}")
	private String EGO_USER_REDIS;
	
	@Value("${EGO_USER_COOKIE}")
	private String EGO_USER_COOKIE;
	
	@Value("${EGO_USER_EXPIRE_TIME}")
	private String EGO_USER_EXPIRE_TIME;
	

	@Override
	public EgoResult check(String param, Integer type) {

		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();

		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		} else if (type == 2) {
			criteria.andPhoneEqualTo(param);
		} else if (3 == type) {
			criteria.andEmailEqualTo(param);
		} else {
			return EgoResult.build(400, "数据有误");
		}

		List<User> list = userMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			return EgoResult.ok(false);
		} else {
			return EgoResult.ok(true);
		}

	}

	@Override
	public EgoResult register(User user) {

		try {
			user.setCreated(new Date());
			user.setUpdated(new Date());;
			userMapper.insert(user);
			
			return EgoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(400, "注册失败");
		}
	}

	@Override
	public EgoResult login(String username, String password,HttpServletResponse response) {
		/**
		 * 1生成Token 令牌
		 * 
		 * 2 将Token 和用户信息写入redis 
		 * 
		 * 3 将token 作为value 写入cookie 
		 * 
		 */
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		List<User> list = userMapper.selectByExample(example);
		
		
		
		if( null !=list && list.size() >0) {
			
			
			User user = list.get(0);
			user.setPassword("");
			/**
			 * 1生成Token 令牌
			 * 
			 * 2 将Token 和用户信息写入redis 
			 * 
			 * 3 将token 作为value 写入cookie 
			 * 
			 */
			String token = UUID.randomUUID().toString();
			
			jedisCluster.set(EGO_USER_REDIS+":"+token,JsonUtils.objectToJson(user));
			jedisCluster.set(EGO_USER_REDIS+":"+token, EGO_USER_EXPIRE_TIME);
			
			Cookie cookie = new Cookie(EGO_USER_COOKIE, token);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			return EgoResult.ok(token);
			
		}else {
			return EgoResult.build(400,"用户名或者密码错误");
		}
		
	}

	@Override
	public EgoResult getUserByToken(String token) {
		
		String jsonData = jedisCluster.get(EGO_USER_REDIS+":"+token);
		if(null != jsonData && !"".equals(jsonData)) {
			jedisCluster.set(EGO_USER_REDIS+":"+token, EGO_USER_EXPIRE_TIME);
			return EgoResult.ok(JsonUtils.jsonToPojo(jsonData, User.class));
		}else {
			return EgoResult.build(400, "当前没有登录");
		}
			
		
	}

	@Override
	public EgoResult logout(String token) {
		jedisCluster.del(EGO_USER_REDIS+":"+token);
		return EgoResult.ok();
	}

}
