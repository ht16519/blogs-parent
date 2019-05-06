package com.xh.blogs.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xh.blogs.api.IMenuService;
import com.xh.blogs.api.IUserService;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.domain.entity.ERoleMenu;
import com.xh.blogs.domain.po.Permission;
import com.xh.blogs.domain.po.Role;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.utils.ShiroUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Name AuthRealm
 * @Description 自定义realm登录授权
 * @Date 2019年1月11日
 * @Author deng.wenqin
 */
public class AuthRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;
	@Autowired
	private IMenuService menuService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principlas) {
		User user = (User) principlas.fromRealm(this.getClass().getName());
		List<String> permisionList = new ArrayList<>();
		List<String> roleList = new ArrayList<>();
		Set<Role> roleSet = user.getRoles();
		if(!CollectionUtils.isEmpty(roleSet)) {
			for (Role role : roleSet) {
				roleList.add(role.getName());
				Set<Permission> permissionSet = role.getPermissions();
				if(!CollectionUtils.isEmpty(permissionSet)) {
					for (Permission permission : permissionSet) {
						permisionList.add(permission.getName());
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permisionList);
		info.addRoles(roleList);
		return info;
	}

	/**
	 * 认证登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String userName = usernamePasswordToken.getUsername();
		User user = userService.getUserInfoByName(userName);
		//存入用户菜单
		ShiroUtil.sessionSetValue(ConfigConst.ADMIN_USER_MENU_CACHE_KEY, menuService.getUserMenuCache(user));
		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
	}


}
