package com.xh.blogs.auth;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @Name CredentialMatcher
 * @Description 自定义 shiro密码比较器 
 * @Date 2019年1月11日
 * @Author deng.wenqin
 */
public class CredentialMatcher extends SimpleCredentialsMatcher{

	/**
	 * 校验用户密码的方法
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String password = new String(usernamePasswordToken.getPassword());
		//获取AuthRealm中存入的密码
		String dbPassword = (String) info.getCredentials();
		//自定义校验规则 
		// TODO
		return this.equals(password, dbPassword);
	}

}
