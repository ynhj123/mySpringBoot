package com.ynhj.nativemysql.controller;

import com.ynhj.nativemysql.common.exception.GlobalException;
import com.ynhj.nativemysql.entiry.ProductEntity;
import com.ynhj.nativemysql.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepo productRepo;

    @GetMapping("/products")
    Flux<ProductEntity> getAll() {
        return this.productRepo.findAll();
    }

    @GetMapping("/error")
    Mono<String> error() {
        throw new GlobalException(HttpStatus.FORBIDDEN);
    }
}
