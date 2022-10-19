package com.ynhj.nativemysql.controller;

import com.ynhj.nativemysql.common.entity.GlobalException;
import com.ynhj.nativemysql.common.entity.R;
import com.ynhj.nativemysql.entiry.ProductEntity;
import com.ynhj.nativemysql.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepo productRepo;

    @GetMapping("/products")
    Mono<R<List<ProductEntity>>> getAll() {
        return R.ok(this.productRepo.findAll().collectList());
    }

    @GetMapping("/error")
    Mono<String> error() {
        throw new GlobalException(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/products/{id}")
    Mono<R<ProductEntity>> getOne(@PathVariable Long id) {
        return R.ok(this.productRepo.findById(id));
    }
}
