package com.ynhj.ingress.controller;

import com.ynhj.ingress.common.entity.R;
import com.ynhj.ingress.entity.dto.LoginUserDto;
import com.ynhj.ingress.entity.vo.LoginUserVo;
import com.ynhj.ingress.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class LoginController {
    private final UserService userService;


    @PostMapping("/login")
    public Mono<R<LoginUserVo>> login(@RequestBody LoginUserDto user) {
        return R.monoOk(userService.login(user));
    }


    @PostMapping("/signup")
    public Mono<R<LoginUserVo>> signUp(@RequestBody LoginUserDto user) {
        return R.monoOk(userService.signup(user));
    }

}
