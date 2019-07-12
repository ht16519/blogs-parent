package com.xh.blogs.exception;


import com.xh.blogs.error.CommomError;

/**
 * @Name BusinessException
 * @Description 业务异常类
 * @Date 2018-03-21 
 * @author deng.wenqin
 */
public class LoginException extends BusinessException{

	public LoginException(CommomError commomError) {
		super(commomError);
	}

	public LoginException(CommomError commomError, String errMsg) {
		super(commomError, errMsg);
	}
}
