package com.ynhj.nativemysql.service;

import com.ynhj.nativemysql.entiry.dto.LoginUserDto;
import com.ynhj.nativemysql.entiry.vo.LoginUserVo;
import reactor.core.publisher.Mono;

/**
 * @date: 2022/10/24
 * @author: yangniuhaojiang
 * @title: UserService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public interface UserService {
    Mono<LoginUserVo> login(LoginUserDto user);

    Mono<LoginUserVo> signup(LoginUserDto user);
}
