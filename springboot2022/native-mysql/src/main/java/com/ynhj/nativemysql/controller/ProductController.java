package com.ynhj.nativemysql.controller;

import com.ynhj.nativemysql.common.entity.GlobalException;
import com.ynhj.nativemysql.common.entity.R;
import com.ynhj.nativemysql.entiry.vo.ProductVo;
import com.ynhj.nativemysql.service.ProductService;
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
    private final ProductService productService;

    @GetMapping("/products")
    Mono<R<List<ProductVo>>> getAll() {
        return R.ok(productService.findAll().collectList());
    }

    @GetMapping("/error")
    Mono<String> error() {
        throw new GlobalException(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/products/{id}")
    Mono<R<ProductVo>> getOne(@PathVariable Long id) {
        return R.ok(productService.findById(id));
    }
}
