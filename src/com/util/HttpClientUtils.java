package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 模拟HTTP请求
 * @author chenchao 991722899@qq.com
 * @date 2014-3-10 下午5:21:58
 */
public class HttpClientUtils {
//	private static final Logger log = Logger.getLogger(HttpClientUtils.class);
	/**
	 * 根据请求地址和请求参数以及请求返回的数据类型来处理一个HTTP请求
	 * @author chenchao 991722899@qq.com
	 * @date 2014-3-10 下午5:32:21
	 * @param requestUrl
	 * @param params
	 * @param reaultType
	 * @return
	 */
	public static <T> T sendRequest(String requestUrl,String params,Class<T> reaultType){
//		log.info("参数：requestUrl = 【"+requestUrl+"】,params = 【"+params+"】,reaultType = 【"+reaultType+"】");
		PrintWriter out = null;
		HttpURLConnection httpURLConnection=null;
		BufferedReader in = null;
		String sResult = "";
		try {
			URL url=new URL(requestUrl);
			httpURLConnection=(HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestProperty("Content-Type", "plain/text; charset=UTF-8");
			httpURLConnection.setRequestMethod("POST");
			System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(30000));
			System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(30000));
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.connect();
			out = new PrintWriter(httpURLConnection.getOutputStream());
			
			out.write(params);
			out.flush();
			in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String line ="";
			 while((line = in.readLine())!= null){
		        if(line.length()>0){
		        	sResult += line;
		        }
		    }
			if(sResult!=null && sResult.length()>0){
				return new Gson().fromJson(sResult,reaultType);
			}
		} catch (Exception e) {
//			log.error("HttpClientUtils.sendRequest()方法出现异常",e);
			e.printStackTrace();
		}finally{
			try {
				if(null!=in)
					in.close();
				if(null!=out)   
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		String   j_username = "xuekui";
		String   j_password = "123";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("j_username", j_username);
		map.put("j_password", j_password);
		String params = "j_username="+j_username+"&j_password="+j_password;
		Gson gson = new Gson();
		String ret = HttpClientUtils.sendRequest("http://localhost:8081/j_spring_security_check?"+params, "", String.class);
		System.out.println(ret);
	}
	
}
