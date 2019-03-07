package com.exception;

public class XException extends Exception {
	private static final long serialVersionUID = 2123939660722594104L;
	private String code;
	private String msg;

	public XException() {
	}

	public XException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public XException(String msg, String code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public XException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public XException(Throwable e, String code) {
		super(e);
		this.code = code;
	}

	public XException(Throwable e) {
		super(e);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static void test() throws Exception{
		a();
		b();
	}
	private static void a() throws XException{
		throw new XException("XException message","5");
	}
	private static void b() throws UserException{
		throw new UserException("UserException message","6");
	}
}

