package com.fatec.gad.model.request;

import com.fatec.gad.model.entity.Role;
import com.fatec.gad.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements UserDetails {

    private String username;

    private String email;

    private String password;

    private String confirmPassword;

    private List<RoleRequest> roles;

    private Collection<? extends GrantedAuthority> authorities;

    protected UserRequest(User user){
        clone(user);
        List<SimpleGrantedAuthority> authorities;
        authorities = this.roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName())))
                .collect(Collectors.toList());
        this.authorities = authorities;
    }

    public static UserRequest create(User user){
        return new UserRequest(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private void clone(User data){
        this.username = data.getUsername();
        this.password = data.getPassword();
        this.email = data.getEmail();
        cloneListRoles(data.getRoles());
    }

    private void cloneListRoles(List<Role> roleList){
        RoleRequest role;
        this.roles = new LinkedList<>();
        for(Role var : roleList){
            role = new RoleRequest();
            role.clone(var);
            this.roles.add(role);
        }
    }
}
