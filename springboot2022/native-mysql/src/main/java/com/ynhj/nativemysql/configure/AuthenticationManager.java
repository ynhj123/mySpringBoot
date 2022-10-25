package com.ynhj.nativemysql.configure;

import com.ynhj.nativemysql.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @date: 2022/10/25
 * @author: yangniuhaojiang
 * @title: AuthenticationManager
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username = JwtUtil.getUsername(authToken);
        return Mono.just(JwtUtil.validateToken(authToken))
                .filter(valid -> valid)
                .switchIfEmpty(Mono.empty())
                .map(valid -> {
                    Claims claims = JwtUtil.checkJWT(authToken);
                    List<String> rolesMap = claims.get("rol", List.class);
                    return new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                    );
                });
    }
}
