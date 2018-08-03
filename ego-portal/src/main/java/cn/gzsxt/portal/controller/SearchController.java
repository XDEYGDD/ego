package cn.gzsxt.portal.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gzsxt.common.pojo.SerachResult;
import cn.gzsxt.portal.service.SearchService;
import redis.clients.jedis.JedisCluster;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@RequestMapping("/search")
	public String query(String q, @RequestParam(defaultValue="1") Integer page,
			ModelMap map,HttpServletRequest request) {
		SerachResult result = searchService.query(q, page);
		//验证单点登录是否有效
//		Cookie[] cookies = request.getCookies();
//		boolean flag = false;
//		if( null !=null && cookies.length >0) {
//			for (Cookie cookie : cookies) {
//				if( "EGO_USER_COOKIE".equals(cookie.getName())) {
//					String token  = cookie.getValue();
//					String jsonData = jedisCluster.get("EGO_UER_REDIE");
//					if( null != jsonData && !jsonData.equals("")) {
//						flag = true;
//						break;
//					}
//				}
//			}
//		}
//		
//		if(flag) {
//			
//		}else {
//			map.addAttribute("message","请先登录" );
//			return "error/exception";
//			
//		}
		
		
		map.addAttribute("query",q);
		map.addAttribute("totalPages",result.getPageCount());
		map.addAttribute("itemList", result.getItemList());
		map.addAttribute("page", page);
		
		return "search";
	}
}
