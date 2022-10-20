package com.ynhj.nativemysql.controller;

import com.ynhj.nativemysql.common.entity.GlobalException;
import com.ynhj.nativemysql.common.entity.R;
import com.ynhj.nativemysql.entiry.dto.ProductDto;
import com.ynhj.nativemysql.entiry.dto.UpdateProductDto;
import com.ynhj.nativemysql.entiry.vo.ProductVo;
import com.ynhj.nativemysql.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    Mono<R<List<ProductVo>>> getAll(@Param("pageSize") Long pageSize, @Param("pageNum") Long pageNum) {
        return R.ok(productService.findAll(pageSize, pageNum).collectList());
    }

    @GetMapping("/error")
    Mono<String> error() {
        throw new GlobalException(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/product/{id}")
    Mono<R<ProductVo>> getOne(@PathVariable Long id) {
        return R.ok(productService.findById(id));
    }

    @PostMapping("/product")
    Mono<R<ProductVo>> insert(@Valid @RequestBody ProductDto productDto) {
        return R.ok(productService.insert(productDto));
    }

    @PutMapping("/product")
    Mono<R<ProductVo>> update(@Valid @RequestBody UpdateProductDto productDto) {
        return R.ok(productService.update(productDto));
    }

    @DeleteMapping("/product/{id}")
    Mono<R<Void>> delete(@PathVariable Long id) {
        return R.ok(productService.delete(id));
    }
}
