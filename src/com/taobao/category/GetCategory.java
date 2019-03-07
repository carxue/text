package com.taobao.category;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GetCategory {
	protected static String Url = "http://gw.api.taobao.com/router/rest";// 正式环境调用地址
	protected static String appkey = "你的AppKey";
	protected static String secret = "你的APPSecret";
	protected static String session = "你的SesionID";
	protected static File file = new File("c:/cats.txt");
	protected static FileOutputStream fop = null;

	// 调用淘宝接口，获取父目录下的子目录（以json数据格式返回）
	public static String getCat(String parent_cid) {
		TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
		apiparamsMap.put("format", "json");// 以json数据格式返回
		apiparamsMap.put("method", "taobao.itemcats.get"); // 获取类目函数
		apiparamsMap.put("sign_method", "md5");
		apiparamsMap.put("app_key", appkey); // appkey
		apiparamsMap.put("v", "2.0");// 版本
		apiparamsMap.put("session", session);// sessionID
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		apiparamsMap.put("timestamp", timestamp);// 时间

		apiparamsMap.put("fields", "cid,parent_cid,name,is_parent");// 需要获取的字段
		apiparamsMap.put("parent_cid", parent_cid); // 父目录id

		// 生成签名
		String sign = Util.md5Signature(apiparamsMap, secret);
		apiparamsMap.put("sign", sign);
		StringBuilder param = new StringBuilder();
		for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<String, String> e = it.next();
			param.append("&").append(e.getKey()).append("=")
					.append(e.getValue());
		}
		return param.toString().substring(1);
	}

	// 根据父目录递归获取子目录，并写入txt文件
	public static void getAllCats(String root) throws IOException {
		String result = Util.getResult(Url, getCat(root));
		// System.out.print(result);
		if (result == null || result.length() == 0) {
			System.out.print("root:" + root + " result null");// 获取不到，记录下来后面再手动补充
			return;
		}
		// String result
		// ="{\"itemcats_get_response\":{\"item_cats\":{\"item_cat\":[{\"cid\":121266001,\"is_parent\":true,\"name\":\"众筹\",\"parent_cid\":0},"
		// +
		// "{\"cid\":120950001,\"is_parent\":true,\"name\":\"保险分销\",\"parent_cid\":0},"
		// +
		// "{\"cid\":124470006,\"is_parent\":true,\"name\":\"平行进口车\",\"parent_cid\":0}]},\"request_id\":\"ze1ym5zcokd2\"}}";
		Pattern p = Pattern.compile("\"item_cat\":(.*)},\"request_id\":");// 提取item_cat部分内容
		Matcher m = p.matcher(result);
		if (m.find()) {
			String catsJsonstr = m.group(1);
			List<item_cat> cats = JSONArray.parseArray(catsJsonstr,
					item_cat.class);
			for (int i = 0; i < cats.size(); i++) {
				item_cat cat = cats.get(i);
				String content = cat.cid + "," + cat.name + ","
						+ cat.parent_cid + "," + cat.is_parent + "\r\n";// 写入内容，一个类目一行记录
				byte[] contentInBytes = content.getBytes();
				fop.write(contentInBytes);// 写入文件
				fop.flush();
				if (cat.is_parent.equals("true")) {
					getAllCats(cat.cid);
				}
			}
		}
	}

	public static void getTaobaoCats() {
		try {
			// 打开文件
			fop = new FileOutputStream(file);
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// 获取顶层根目录（0）
			getAllCats("0");
			// 关闭文件
			fop.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// 获取所有类目，并写入txt文件
		getTaobaoCats();
	}
}
