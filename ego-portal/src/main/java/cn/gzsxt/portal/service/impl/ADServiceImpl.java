package cn.gzsxt.portal.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.gzsxt.common.utils.JsonUtils;
import cn.gzsxt.manager.pojo.Content;
import cn.gzsxt.portal.pojo.ADPicture;
import cn.gzsxt.portal.service.ADService;

@Service
public class ADServiceImpl implements ADService {

	@Value("${AD_REST_URL}")
	private String AD_REST_URL;
	
	
	@Override
	public List<ADPicture> getADS() {
		// 1传建客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 2创建get请求
		List<ADPicture> adlist = null;
		HttpGet get = new HttpGet(AD_REST_URL);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			adlist = new ArrayList<>();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				String content = EntityUtils.toString(entity, "UTF-8");
				
				if( null!=content && !"".equals(content)) {
					List<Content> list = JsonUtils.jsonToList(content, Content.class);
					ADPicture ad = null;
					for (Content c : list) {
						ad = new ADPicture();
						ad.setAlt(c.getTitle());
						ad.setHref(c.getUrl());
						ad.setSrc(c.getPic());
						ad.setScrB(c.getPic2());
						adlist.add(ad);
					}
				}
				
			}

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return adlist;
	}

}
