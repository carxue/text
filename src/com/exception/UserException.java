package com.exception;

import org.apache.commons.lang.StringUtils;

public class UserException extends Exception {

	private static final long serialVersionUID = 5696258901927258154L;
	private String resultCode = StringUtils.EMPTY;

	private String msg;

	public UserException() {

	}

	public UserException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public UserException(String msg, String resultCode) {
		super(msg);
		this.msg = msg;
		this.resultCode = resultCode;
	}

	public UserException(Throwable cause, String resultCode) {
		super(cause);
		this.resultCode = resultCode;
	}

	public UserException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UserException(Throwable cause) {
		super(cause);
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
