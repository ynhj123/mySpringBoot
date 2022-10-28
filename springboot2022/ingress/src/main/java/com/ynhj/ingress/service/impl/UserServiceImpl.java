package com.ynhj.ingress.service.impl;

import com.ynhj.ingress.common.entity.R;
import com.ynhj.ingress.entity.dto.LoginUserDto;
import com.ynhj.ingress.entity.vo.LoginUserVo;
import com.ynhj.ingress.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
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
    final WebClient webClient;

    @Override
    public Mono<R<LoginUserVo>> login(LoginUserDto user) {
        return webClient.post().uri("http://localhost:8080/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .bodyToMono(R.class)
                .map(r -> (R<LoginUserVo>) r);
    }

    @Override
    public Mono<R<LoginUserVo>> signup(LoginUserDto user) {
        return webClient.post().uri("http://localhost:8080/auth/signup")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<R<LoginUserVo>>() {
                });
    }

}
