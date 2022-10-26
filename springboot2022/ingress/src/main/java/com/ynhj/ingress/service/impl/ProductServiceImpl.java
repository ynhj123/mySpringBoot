package com.ynhj.ingress.service.impl;

import com.ynhj.ingress.entity.dto.ProductDto;
import com.ynhj.ingress.entity.dto.UpdateProductDto;
import com.ynhj.ingress.entity.vo.ProductVo;
import com.ynhj.ingress.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @date: 2022/10/19
 * @author: yangniuhaojiang
 * @title: ProductServiceImpl
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final WebClient webClient;

    @Override
    public Flux<ProductVo> findAll(Long pageSize, Long pageNum) {
        return null;
    }

    @Override
    public Mono<ProductVo> findById(Long id) {
        return null;
    }


    @Override
    public Mono<ProductVo> insert(ProductDto productDto) {

        return null;
    }

    @Override
    public Mono<ProductVo> update(UpdateProductDto productDto) {
        return null;
    }

    @Override
    public Mono<Void> delete(Long id) {
        return null;
    }
}
