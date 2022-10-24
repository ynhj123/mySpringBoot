package com.ynhj.nativemysql.service.impl;

import com.ynhj.nativemysql.common.utils.JwtUtil;
import com.ynhj.nativemysql.common.utils.SnowflakeIdUtils;
import com.ynhj.nativemysql.entiry.dto.LoginUserDto;
import com.ynhj.nativemysql.entiry.po.UserPo;
import com.ynhj.nativemysql.entiry.vo.LoginUserVo;
import com.ynhj.nativemysql.repository.UserRepository;
import com.ynhj.nativemysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @date: 2022/10/24
 * @author: yangniuhaojiang
 * @title: UserServiceImpl
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder password = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public Mono<LoginUserVo> login(LoginUserDto user) {
        return Mono.just(user.getUsername())
                .flatMap(userRepository::findByUsername)
                .filter(it -> password.matches(user.getPassword(), it.getPassword()))
                .map(it -> getLoginUserVo(it));


    }

    private LoginUserVo getLoginUserVo(UserPo it) {
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setUsername(it.getUsername());
        String token = JwtUtil.createToken(it.getUsername(), "");
        loginUserVo.setToken(token);
        return loginUserVo;
    }

    @Override
    public Mono<LoginUserVo> signup(LoginUserDto user) {
        return Mono.just(user.getUsername())
                .flatMap(userRepository::findByUsername)
                .filter(it -> password.matches(user.getPassword(), it.getPassword()))
                .switchIfEmpty(save(user))
                .map(it -> getLoginUserVo(it));
    }

    private Mono<UserPo> save(LoginUserDto user) {
        UserPo userPo = new UserPo();
        userPo.setId(SnowflakeIdUtils.next());
        userPo.setUsername(user.getUsername());
        userPo.setPassword(password.encode(user.getPassword()));
        return userRepository.save(userPo);
    }


}
