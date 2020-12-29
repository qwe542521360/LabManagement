package com.njust.vuelab.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.njust.vuelab.entity.Account;
import com.njust.vuelab.service.AccountService;
import com.njust.vuelab.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AccountService accountService;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken)authenticationToken;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        Account account = accountService.getById(userId);
        if (account == null){
            throw new UnknownAccountException("账户不存在");
        }

        Accountprofile accountprofile = new Accountprofile();
        BeanUtil.copyProperties(account,accountprofile);

        return new SimpleAuthenticationInfo(accountprofile,jwtToken.getCredentials(),getName());
    }
}
