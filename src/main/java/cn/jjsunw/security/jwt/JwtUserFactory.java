package cn.jjsunw.security.jwt;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import cn.jjsunw.model.Role;
import cn.jjsunw.model.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getEnable(),
                user.getLastPwdResetTime()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getCode()))
                .collect(Collectors.toList());
    }
}
