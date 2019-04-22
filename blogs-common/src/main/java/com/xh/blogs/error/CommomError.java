package com.xh.blogs.error;

/**
 * @Name CommomError
 * @Description 公共错误接口
 * @Date 2018-03-21 
 * @author deng.wenqin
 */
public interface CommomError {

	int getErrCode();
	
	String getErrMsg();
	
	CommomError setErrMsg(String errMsg);
}
