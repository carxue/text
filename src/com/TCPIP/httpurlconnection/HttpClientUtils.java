package com.TCPIP.httpurlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;

public class HttpClientUtils {

	public static <T> T sendRequest(String requestUrl, String params,
			Class<T> reaultType) {
		Gson gson = new Gson();
		PrintWriter out = null;
		HttpURLConnection httpURLConnection = null;
		BufferedReader in = null;
		String sResult = "";
		try {
			URL url = new URL(requestUrl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			System.setProperty("sun.net.client.defaultConnectTimeout",
					String.valueOf(30000));
			System.setProperty("sun.net.client.defaultReadTimeout",
					String.valueOf(30000));
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.connect();
			out = new PrintWriter(httpURLConnection.getOutputStream());

			out.write(params);
			out.flush();
			in = new BufferedReader(new InputStreamReader(
					httpURLConnection.getInputStream()));
			String line = "";
			while ((line = in.readLine()) != null) {
				if (line.length() > 0) {
					sResult += line;
				}
			}
			if (sResult != null && sResult.length() > 0) {
				return new Gson().fromJson(sResult, reaultType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		
		@SuppressWarnings("unchecked")
		Map<String,Object> result=HttpClientUtils.sendRequest("http://localhost:8081/searchService/admin/reAppIndex.xhtml","type=all",Map.class);
		System.out.println(new Gson().toJson(result));
//		HttpClientUtils.sendRequest("http://localhost:8081/searchService/finance/solrfinances.xhtml", "?current=9", null);

	}
}
