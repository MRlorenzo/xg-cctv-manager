package com.xg.cctv.shiro.realm;

import com.xg.cctv.common.StatusCode;
import com.xg.cctv.exception.RRException;
import com.xg.cctv.mybatis.po.SysPermission;
import com.xg.cctv.mybatis.po.SysUser;
import com.xg.cctv.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    public SysUserService iSysUserService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser)principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();

        List<SysPermission> permissions = user.getRoutes();
        Set<String> permsSet = iSysUserService.permissions2set(permissions);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.setStringPermissions(permsSet);

        return info;
    }

    /**
     * 检查账号
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        //查询用户信息
        SysUser user = iSysUserService.selectByUsername(username);

        //账号不存在
        if(user == null) {
            System.out.println("账号不存在");
            throw new UnknownAccountException();
        }

        //账号锁定
        if( false ){
            throw new RRException("AccountLocked" , StatusCode.USER_ACCOUNT_LOCKED);
        }

        return new SimpleAuthenticationInfo(user,  user.getPassword() , getName());

    }
}
