package cn.gzsxt.portal.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TestHttpClient {

	public static void main(String[] args) {
		//1传建客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//2创建get请求
		
		HttpGet get = new HttpGet("http://localhost:8081/rest/content/89");
		CloseableHttpResponse response = null;
		try {
			 response = httpClient.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			
			if(statusCode == 200) {
				HttpEntity entity = response.getEntity();
				String content = EntityUtils.toString(entity,"UTF-8");
				System.out.println(content);
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(response !=null) {
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
		
	}
}
