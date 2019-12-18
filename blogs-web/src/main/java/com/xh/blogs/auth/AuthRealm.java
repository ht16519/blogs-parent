package com.xh.blogs.auth;

import com.xh.blogs.api.IMenuService;
import com.xh.blogs.api.IUserService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.domain.entity.ERoleMenu;
import com.xh.blogs.domain.po.Role;
import com.xh.blogs.domain.po.User;
import com.xh.blogs.utils.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
		User user = (User) principlas.getPrimaryPrincipal();
		//获取用户角色
		List<String> roleList = new ArrayList<>();
		Set<Role> roleSet = user.getRoles();
		if(!CollectionUtils.isEmpty(roleSet)) {
			for (Role role : roleSet) {
				roleList.add(role.getName());
			}
		}
		//获取用户权限
		List<String> permisionList = new ArrayList<>();
		Object o = ShiroUtil.sessionGetValue(ConfigConst.ADMIN_USER_MENU_CACHE_KEY);
		if(o != null){
			Set<ERoleMenu> menuSet = (Set<ERoleMenu>)o;
			if(!CollectionUtils.isEmpty(menuSet)) {
				for (ERoleMenu roleMenu : menuSet) {
					if(StringUtils.isNotEmpty(roleMenu.getPermission())){
						permisionList.add(roleMenu.getPermission());
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
		//用户不存在
		if(user == null){
            throw new UnknownAccountException(userName);
        }
        //用户冻结
        if(user.getStatus().equals(CommonConst.INVALID_STATUS)){
            throw new LockedAccountException(userName);
        }
		//存入用户菜单
		ShiroUtil.sessionSetValue(ConfigConst.ADMIN_USER_MENU_CACHE_KEY, menuService.getUserMenuCache(user));
		return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
	}


}
