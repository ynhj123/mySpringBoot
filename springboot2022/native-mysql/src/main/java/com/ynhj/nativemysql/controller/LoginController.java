package com.ynhj.nativemysql.controller;

import com.alibaba.fastjson2.JSON;
import com.ynhj.nativemysql.common.entity.R;
import com.ynhj.nativemysql.entiry.dto.LoginUserDto;
import com.ynhj.nativemysql.entiry.vo.LoginUserVo;
import com.ynhj.nativemysql.repository.UserRepository;
import io.jsonwebtoken.impl.crypto.JwtSigner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@Slf4j
public class LoginController {
    private final UserRepository userRepository;
//    private final MyUserService myUserService;
    private final JwtSigner jwtSigner;
    private final PasswordEncoder password = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @PostMapping("login")
    public Mono<R<LoginUserVo>> login(@RequestBody LoginUserDto user) {

        return Mono.just(user.getUsername())
                .flatMap(userRepository::findByUsername)
                .filter(it -> password.matches(user.getPassword(), it.getPassword()))
                .map(it -> new LoginUserVo(it.getUsername(), "", jwtSigner.sign(JSON.toJSONString(it))))
                .map(loginUserVo -> R.monoOk(loginUserVo))
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(R.monoFail(HttpStatus.UNAUTHORIZED.value(), "登录失败", new LoginUserVo()));
    }


    @PostMapping("signup")
    public Mono<R<Integer>> signUp(@RequestBody LoginUserDto user) {

        return Mono.just(user)
//                .map(myUserService::save)
                .map(it -> R.monoOk(HttpStatus.OK.value()))
                .onErrorResume(e -> R.monoFail(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), 0));
    }

}
