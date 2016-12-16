package com.pm.tool.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 3pa调用
 * 
 * @author zhangliangming 2016-10-17
 * 
 */
public class WTBDao {

	/**
	 * 核心正式路径
	 */
//	private final String BASE_URL = "http://core.wetoband.com";
	/**
	 * 核心测试路径
	 */
	private final String BASE_URL = "http://test_core.wetoband.com";
	/**
	 * 吾托帮正式web路径
	 */
//	private final String WEB_URL = "http://www.wetoband.com";
	/**
	 * 吾托帮测试web路径
	 */
	private final String WEB_URL = "http://test.wetoband.com";
	/**
	 * 应用id
	 */
	private final String aid = "35937856243175850473";
//	private final String aid = "56637839250724022721";
//	private final String aid = "95777949094586035428";
	/**
	 * 必须参数
	 */
	private String baseParams = "aid=" + aid + "&format=json";

	/**
	 * 互动室类型
	 * 
	 * @author zhangliangming
	 * 
	 */
	public class ChatroomType {
		/**
		 * 普通互动室
		 */
		public static final String CHATROOM = "CHATROOM";
	};

	/**
	 * http请求方法
	 * 
	 * @author zhangliangming
	 * 
	 */
	public class HttpMethod {
		public static final int PUT = 0;
		public static final int POST = 1;
		public static final int DELETE = 2;
		public static final int GET = 3;
	}

	private String accessToken;

	public WTBDao(String accessToken) {
		this.accessToken = accessToken;
		baseParams += "&access_token=" + accessToken;
	}

	public static void main(String[] args) {
		WTBDao dao = new WTBDao("");
		String accessToken = dao.getAccessToken("baokeyong","baokeyong");
		System.out.println(accessToken);

	}

	/**
	 * 登录接口
	 * 
	 * @param userAccount
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @author zhangliangming 2016-10-18
	 */
	public String getAccessToken(String userAccount, String password) {
		String url = BASE_URL + "/core/v4/authc/user?" + "aid=" + aid
				+ "&format=json";
		List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("name", userAccount));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("rememberMe", "true"));

		// System.out.println("请求的url:" + url + "&"
		// + URLEncodedUtils.format(params, "UTF-8"));
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost postMethod = new HttpPost(url);
		try {
			postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = httpClient.execute(postMethod);
			// 读取cookie并保存文件
			List<Cookie> cookies = ((AbstractHttpClient) httpClient)
					.getCookieStore().getCookies();
			if (!cookies.isEmpty()) {
				for (int i = 0; i < cookies.size(); i++) {
					if (cookies.get(i).getName().equals("access_token")) {
						return cookies.get(i).getValue();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 * @author zhangliangming 2016-10-18
	 */
	public String loginout() {
		String url = BASE_URL + "/core/v4/authc/user?" + baseParams;
		return invoke3PAInterface(url, null, HttpMethod.DELETE);
	}

	/**
	 * 创建互动室
	 * 
	 * @param roomUserNumber
	 *            互动室最大人数
	 * @param name
	 *            互动室名字
	 * @param keywords
	 *            互动室关键字
	 * @param description
	 *            互动室描述
	 * @param chatroomType
	 *            互动室类型【ChatroomType】
	 * @param bandID
	 *            挂载的帮区ID
	 * @return
	 * @author zhangliangming 2016-10-18
	 */
	public String createChatRoom(String roomUserNumber, String name,
			String keywords, String description, String chatroomType,
			String bandID) {
		String url = BASE_URL + "/core/v4/chatroom?" + baseParams;
		JSONObject chatRoomData = new JSONObject();
		chatRoomData.put("name", name);
		chatRoomData.put("keywords", keywords);
		chatRoomData.put("roomUserNumber", roomUserNumber);
		chatRoomData.put("description", description);
		chatRoomData.put("chatroomType", chatroomType);
		List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("chatroom", chatRoomData.toString()));
		params.add(new BasicNameValuePair("bandID", bandID));
		return invoke3PAInterface(url, params, HttpMethod.PUT);
	}
	
	/**
	 * 调用3pa
	 * 
	 * @param url
	 *            请求url
	 * @param params
	 *            参数列表
	 * @param httpMethod
	 *            http请求方法类型
	 * @return
	 * @author zhangliangming 2016-10-18
	 */
	private String invoke3PAInterface(String url,
			List<BasicNameValuePair> params, int httpMethod) {
		switch (httpMethod) {
		case HttpMethod.PUT:
			return invoke3PAPutInterface(url, params);
		case HttpMethod.POST:
			return invoke3PAPostInterface(url, params);
		case HttpMethod.DELETE:
			return invoke3PADeleteInterface(url, params);
		case HttpMethod.GET:
			return invoke3PAGETInterface(url, params);
		default:
			break;
		}
		return null;

	}

	/**
	 * 调用3pa的put方法
	 * 
	 * @param url
	 *            请求url
	 * @param params
	 *            参数
	 * @return
	 * @author zhangliangming 2016-10-18
	 */
	private String invoke3PAPutInterface(String url,
			List<BasicNameValuePair> params) {
		if (params == null)
			params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("_method", "put"));
		return invoke3PAPostInterface(url, params);
	}

	/**
	 * 调用3pa的post方法
	 * 
	 * @param url
	 *            请求url
	 * @param params
	 *            参数
	 * @return
	 * @author zhangliangming 2016-10-18
	 */
	private String invoke3PAPostInterface(String url,
			List<BasicNameValuePair> params) {
		if (params == null)
			params = new ArrayList<BasicNameValuePair>();
		HttpClient httpClient = new DefaultHttpClient();

//		System.out.println("请求的url:" + url + "&"
//				+ URLEncodedUtils.format(params, "UTF-8"));
		HttpPost postMethod = new HttpPost(url);
		try {
			postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = httpClient.execute(postMethod);
			return EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 调用3pa的delete方法
	 * 
	 * @param url
	 *            请求url
	 * @param params
	 *            参数
	 * @return
	 * @author zhangliangming 2016-10-18
	 */
	private String invoke3PADeleteInterface(String url,
			List<BasicNameValuePair> params) {
		if (params == null)
			params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("_method", "delete"));
		return invoke3PAPostInterface(url, params);
	}

	/**
	 * 调用3pa的get方法
	 * 
	 * @param url
	 *            请求url
	 * @param params
	 *            参数
	 * @return
	 * @author zhangliangming 2016-10-18
	 */
	private String invoke3PAGETInterface(String url,
			List<BasicNameValuePair> params) {
		if (params != null && params.size() != 0) {
			url += "&" + URLEncodedUtils.format(params, "UTF-8");
		} else {
			params = new ArrayList<BasicNameValuePair>();
		}

		System.out.println("请求的url:" + url + "&"
				+ URLEncodedUtils.format(params, "UTF-8"));

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet getMethod = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(getMethod);
			return EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
