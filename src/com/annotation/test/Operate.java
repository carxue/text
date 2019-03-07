package com.annotation.test;

public class Operate {
	
	@MethodAnnotation(mappingclazz = "Operate", mappingmethod = "select_user")
	public static String selectUser(String name) {
		String response = name + " :您好,欢迎你的到来";
		return response;
	}

	@MethodAnnotation(mappingclazz = "Operate", mappingmethod = "select_list")
	public static String selectList(String param) {
		String response = "成功反应用户列表";
		return response;
	}
}
