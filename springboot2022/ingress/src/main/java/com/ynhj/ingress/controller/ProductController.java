package com.ynhj.ingress.controller;

import com.ynhj.ingress.common.entity.GlobalException;
import com.ynhj.ingress.common.entity.R;
import com.ynhj.ingress.entity.dto.ProductDto;
import com.ynhj.ingress.entity.dto.UpdateProductDto;
import com.ynhj.ingress.entity.vo.ProductVo;
import com.ynhj.ingress.service.ProductService;
import lombok.RequiredArgsConstructor;
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
    public Mono<R<List<ProductVo>>> getAll(@RequestParam("pageSize") Long pageSize, @RequestParam("pageNum") Long pageNum) {
        return R.monoOk(productService.findAll(pageSize, pageNum).collectList());
    }

    @GetMapping("/error")
    public Mono<String> error() {
        throw new GlobalException(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/product/{id}")
    public Mono<R<ProductVo>> getOne(@PathVariable Long id) {
        return R.monoOk(productService.findById(id));
    }

    @PostMapping("/product")
    public Mono<R<ProductVo>> insert(@Valid @RequestBody ProductDto productDto) {
        return R.monoOk(productService.insert(productDto));
    }

    @PutMapping("/product")
    public Mono<R<ProductVo>> update(@Valid @RequestBody UpdateProductDto productDto) {
        return R.monoOk(productService.update(productDto));
    }

    @DeleteMapping("/product/{id}")
    Mono<R<Void>> delete(@PathVariable Long id) {
        return R.monoOk(productService.delete(id));
    }
}
