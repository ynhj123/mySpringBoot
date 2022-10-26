package com.ynhj.ingress.service.impl;

import com.ynhj.ingress.entity.dto.LoginUserDto;
import com.ynhj.ingress.entity.vo.LoginUserVo;
import com.ynhj.ingress.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public Mono<LoginUserVo> login(LoginUserDto user) {
        return null;
    }

    @Override
    public Mono<LoginUserVo> signup(LoginUserDto user) {
        return null;
    }

}
