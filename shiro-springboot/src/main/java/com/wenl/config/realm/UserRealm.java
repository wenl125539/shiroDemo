package com.wenl.config.realm;

import com.wenl.Service.UserService;
import com.wenl.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的Realm UserRealM
 */
public class UserRealm extends AuthorizingRealm  {

    @Autowired
    private UserService service;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权操作");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add ");//登陆经过这里 都会给该用户添加这权限 //查数据库
        //1.拿到当前用户对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
           //设置当前用户的权限  currentUser.getPerms 不能为空
        if(currentUser.getPerms()!= null){
            info.addStringPermission(currentUser.getPerms());
        }
        return info ;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证操作");
/*
        //模拟数据库获取用户账户密码
        String username = "wenl";
        String password = "123";*/

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //连接真实数据库
        User user = service.findUserByName(token.getUsername());

        //校验用户名
        if(user == null){//用户名不匹配
            return null; //抛出异常UnknownAccountException 用户名错误
        }

        //保存session 给前端判断
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

        //校验密码 shiro做
        //new SimpleAuthenticationInfo(查询出来的用户（给授权用 ）,user.getPwd(),盐值加密  )
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
        
    }
}
