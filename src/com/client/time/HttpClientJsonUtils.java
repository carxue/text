package com.client.time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * http调用工具类
 * 
 * @author qiuyuanshan
 *
 */
public class HttpClientJsonUtils {
	private final static Logger LOG = LoggerFactory.getLogger(HttpClientJsonUtils.class);
	private static List<Integer[]> thirdTypeList = new ArrayList<Integer[]>(10);

	static {
		Integer[] a = { 1, 2, 3 };
		Integer[] b = { 1, 2, 4 };
		Integer[] c = { 1, 2, 5 };
		Integer[] d = { 1, 3, 4 };
		Integer[] e = { 1, 3, 5 };
		Integer[] f = { 1, 4, 5 };
		Integer[] g = { 2, 3, 4 };
		Integer[] h = { 2, 3, 5 };
		Integer[] i = { 2, 4, 5 };
		Integer[] j = { 3, 4, 5 };
		thirdTypeList.add(a);
		thirdTypeList.add(b);
		thirdTypeList.add(c);
		thirdTypeList.add(d);
		thirdTypeList.add(e);
		thirdTypeList.add(f);
		thirdTypeList.add(g);
		thirdTypeList.add(h);
		thirdTypeList.add(i);
		thirdTypeList.add(j);
	}

	public static final MediaType FORM_HEADER_JSON = MediaType.parse("application/json; charset=utf-8");

	public static final MediaType ACCEPT = MediaType.parse("application/json, text/javascript, */*; q=0.01");

	public static final MediaType Content_Type = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");

	public static String post(String serverUrl, String data) {
		return post(serverUrl, data, null);
	}

	@SuppressWarnings("finally")
	public static String postJson(String httpUrl, String date) {
		String resp = null;
		try {
			URL url = new URL(httpUrl); // url地址
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", Content_Type.toString());
			connection.setRequestProperty("Accept", FORM_HEADER_JSON.toString());
			connection.connect();
			try (OutputStream os = connection.getOutputStream()) {
				os.write(date.getBytes("UTF-8"));
			}
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String lines;
				StringBuffer sbf = new StringBuffer();
				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes(), "UTF-8");
					sbf.append(lines);
				}
				LOG.info(">>>>>>>>>>>>用户请求的json结果为:{}", sbf);
				resp = sbf.toString();
			}
			connection.disconnect();
		} catch (Exception e) {
			LOG.error(">>>>>>>>>>>>http.json请求接口异常:{}", e.getMessage());
		} finally {
			return resp;
		}
	}

	public static String post(String serverUrl, String data, String auth) {
		StringBuilder responseBuilder = null;
		BufferedReader reader = null;
		OutputStreamWriter wr = null;

		URL url;
		try {
			url = new URL(serverUrl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Accept-Charset", "utf-8");
			if (!StringTool.isEmpty(auth)) {
				conn.setRequestProperty("Authorization", "BasicAuth " + auth);
			}
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			InetAddress address = InetAddress.getLocalHost();
			String ip = address.getHostAddress();
			conn.setRequestProperty("ip", ip);
			wr = new OutputStreamWriter(conn.getOutputStream());

			wr.write(data);
			wr.flush();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			responseBuilder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				responseBuilder.append(line).append("\n");
			}
			LOG.debug(responseBuilder.toString());
			return responseBuilder.toString();
		} catch (IOException e) {
			LOG.error("", e);
			return null;
		} finally {

			if (wr != null) {
				try {
					wr.close();
				} catch (IOException e) {
					LOG.error("close error", e);
				}
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					LOG.error("close error", e);
				}
			}

		}
	}

	/**
	 * POST FORM
	 * 
	 * @param url
	 * @param form
	 * @return
	 * @throws IOException
	 */
	private static String postForm(String url, String form) throws IOException {
		LOG.info("  url --> :" + url + "  form:" + form);
		RequestBody body = RequestBody.create(FORM_HEADER_JSON, form);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = new OkHttpClient().newCall(request).execute();
		return response.body().string();
	}

	public static void main(String[] args) throws IOException {
		getEveryDayData(20170101);
	}

	public static List<CqTimeLottery> getEveryDayData(int selectDay) {
		List<CqTimeLottery> cqList = new ArrayList<CqTimeLottery>();
		CqTimeLottery cq = new CqTimeLottery();
		String url = "http://39.108.143.25:8080/stock/cqssc/getCurrentDay.sc?selectDay=" + selectDay;
		Gson gson = new Gson();
		String json = gson.toJson(cq);
		String result = HttpClientJsonUtils.postJson(url, json);
		List<CaipiaoRes> list = gson.fromJson(result, new TypeToken<List<CaipiaoRes>>() {
		}.getType());
		for (CaipiaoRes res : list) {
			String number = res.getNum();
			if (StringUtils.isNotBlank(number)) {
				CqTimeLottery lottery = new CqTimeLottery();
				lottery.setOpenDate(selectDay);
				lottery.setSortNo(res.getNo());
				lottery.setOpenNum(number);
				lottery.setZuThirdPosition123(getAnyThirdNumType(number, thirdTypeList.get(0)));
				lottery.setZuThirdPosition124(getAnyThirdNumType(number, thirdTypeList.get(1)));
				lottery.setZuThirdPosition125(getAnyThirdNumType(number, thirdTypeList.get(2)));
				lottery.setZuThirdPosition134(getAnyThirdNumType(number, thirdTypeList.get(3)));
				lottery.setZuThirdPosition135(getAnyThirdNumType(number, thirdTypeList.get(4)));
				lottery.setZuThirdPosition145(getAnyThirdNumType(number, thirdTypeList.get(5)));
				lottery.setZuThirdPosition234(getAnyThirdNumType(number, thirdTypeList.get(6)));
				lottery.setZuThirdPosition235(getAnyThirdNumType(number, thirdTypeList.get(7)));
				lottery.setZuThirdPosition245(getAnyThirdNumType(number, thirdTypeList.get(8)));
				lottery.setZuThirdPosition345(getAnyThirdNumType(number, thirdTypeList.get(9)));
				lottery.setZuFive(getFiveNumType(number));
				// System.out.println(gson.toJson(lottery));
				cqList.add(lottery);
			}
		}
		return cqList;
	}

	public static void test() throws IOException {
		Integer Date = 20181113;
		String url = "http://39.108.143.25:8080/stock/cqssc/getCurrentDay.sc?selectDay=" + Date;
		CqTimeLottery cq = new CqTimeLottery();
		Gson gson = new Gson();
		String resultJson = postJson(url, gson.toJson(cq));
		List<CaipiaoRes> timeList = gson.fromJson(resultJson, new TypeToken<List<CaipiaoRes>>() {
		}.getType());
		for (CaipiaoRes time : timeList) {
			String number = time.getNum();
			if (StringUtils.isNotBlank(number)) {
				System.out.println(time.getNo() + ":" + number + " : " + getThirdNumType(number, 1) + " : "
						+ getThirdNumType(number, 2) + " : " + getThirdNumType(number, 3) + " : "
						+ getFiveNumType(number));
			}
		}
	}

	public static String getAnyThirdNumType(String openNum, Integer[] thirdType) {
		int a = thirdType[0] - 1, b = thirdType[1] - 1, c = thirdType[2] - 1;
		char[] strChars = openNum.toCharArray();
		char one = strChars[a];
		char two = strChars[b];
		char third = strChars[c];
		if ((one == two && two != third) || (one == third && one != two) || (one != two && two == third)) {
			return "组三";
		}
		if (one == two && one == third) {
			return "豹子";
		}
		return "组六";
	}

	public static String getThirdNumType(String openNum, int thirdType) {
		int a = thirdType - 1, b = thirdType, c = thirdType + 1;
		char[] strChars = openNum.toCharArray();
		char one = strChars[a];
		char two = strChars[b];
		char third = strChars[c];
		if ((one == two && two != third) || (one == third && one != two) || (one != two && two == third)) {
			return "组三";
		}
		if (one == two && one == third) {
			return "豹子";
		}
		return "组六";
	}

	public static String getFiveNumType(String openNum) {
		char[] strChars = openNum.toCharArray();
		Map<String, Integer> map = new HashMap<>();
		for (char c : strChars) {
			String key = c + "";
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}
		int length = map.size();
		if (length == 1) {
			return "五通";
		} else if (length == 2) {
			Set<String> set = map.keySet();
			String fiveType = null;
			for (Iterator<String> in = set.iterator(); in.hasNext();) {
				int value = map.get(in.next());
				if (value == 3) {
					fiveType = "组10";
					break;
				}
				if (value == 4) {
					fiveType = "组5";
					break;
				}
			}
			return fiveType;
		} else if (length == 3) {
			Set<String> set = map.keySet();
			String fiveType = null;
			for (Iterator<String> in = set.iterator(); in.hasNext();) {
				int value = map.get(in.next());
				if (value == 2) {
					fiveType = "组30";
					break;
				}
				if (value == 3) {
					fiveType = "组20";
					break;
				}
			}
			return fiveType;
		} else if (length == 4) {
			return "其他";
		} else {
			return "其他";
		}
	}


}
