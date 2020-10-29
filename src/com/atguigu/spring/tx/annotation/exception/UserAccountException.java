package com.atguigu.spring.tx.annotation.exception;

public class UserAccountException extends RuntimeException{
	static final long serialVersionUID = -7034897193246939L;

	public UserAccountException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAccountException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserAccountException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserAccountException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserAccountException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
