package com.ynhj.nativemysql.controller;

import com.ynhj.nativemysql.common.entity.R;
import com.ynhj.nativemysql.entiry.dto.LoginUserDto;
import com.ynhj.nativemysql.entiry.vo.LoginUserVo;
import com.ynhj.nativemysql.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final UserService userService;


    @PostMapping("login")
    public Mono<R<LoginUserVo>> login(@RequestBody LoginUserDto user) {
        return R.monoOk(userService.login(user));
    }


    @PostMapping("signup")
    public Mono<R<LoginUserVo>> signUp(@RequestBody LoginUserDto user) {
        return R.monoOk(userService.signup(user));
    }

}
