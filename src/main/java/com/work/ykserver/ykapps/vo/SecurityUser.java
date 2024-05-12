package com.work.ykserver.ykapps.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.work.ykserver.ykapps.pojo.User;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 用户安全类
 */
@Data
public class SecurityUser implements UserDetails {

    //private static final long serialVersionUID = -4077982971235279195L;
    // 用户对象
    private User user;

    // 权限列表
    @JsonIgnore
    private List<SimpleGrantedAuthority> authorityList;

    public SecurityUser() {
    }

    public SecurityUser(User user, List<SimpleGrantedAuthority> authorityList) {
        this.user = user;
        this.authorityList = authorityList;
    }

    public SecurityUser(User user) {
        this.user = user;
    }


    public void setAuthorityList(List<SimpleGrantedAuthority> authorityList) {
        this.authorityList = authorityList;
    }

    /**
     * 获取当前用户角色信息
     * @return
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getLoginPwd();
    }

    /**
     * 检查账号是否存在
     * @return
     */
    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getName();
    }

    /**
     * 检查账号是否过期
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountNoExpired() == 1;
    }

    /**
     * 检查账号是否锁定
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountNoLocked() == 1;
    }

    /**
     * 检查账号密码是否过期
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsNoExpired() == 1;
    }

    /**
     * 检查账号是否启用
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return user.getAccountEnabled() == 1;
    }
}
