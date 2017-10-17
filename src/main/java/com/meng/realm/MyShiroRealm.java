package com.meng.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.meng.entity.Blogger;
import com.meng.service.BloggerService;

public class MyShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private BloggerService bloggerService;
	
	// 假定一个校验数据
	//private static final String USER_NAME = "844125097@qq.com";
	//private static final String PASS_WORD = "123456";

	/**
	 * 用户权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		
		Set<String> roleNames = new HashSet<>();
		Set<String> permissions = new HashSet<>();
		
		// 添加角色
		roleNames.add("administrator");
		// 添加权限
		permissions.add("test/newPage");
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		
		info.setStringPermissions(permissions);
		
		return info;
	}
	
	/**
	 * 用户登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		String username = authcToken.getPrincipal().toString();
		
		Blogger blogger = bloggerService.getBloggerByName(username);
		
		//UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		if (blogger != null) {
			return new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(), getName());
		} else {
			throw new AuthenticationException();
		}
	}

}
