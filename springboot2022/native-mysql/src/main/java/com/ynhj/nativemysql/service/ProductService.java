package com.ynhj.nativemysql.service;

import com.ynhj.nativemysql.entiry.dto.ProductDto;
import com.ynhj.nativemysql.entiry.dto.UpdateProductDto;
import com.ynhj.nativemysql.entiry.vo.ProductVo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @date: 2022/10/19
 * @author: yangniuhaojiang
 * @title: ProductService
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public interface ProductService {
    @Cacheable(value = "product-list")
    Flux<ProductVo> findAll();

    @Cacheable(value = "product-id", key = "#p0")
    Mono<ProductVo> findById(Long id);

    @CacheEvict(cacheNames = {"product-list"}, allEntries = true)
    Mono<ProductVo> insert(ProductDto productDto);

    @CacheEvict(cacheNames = "product-id", key = "#p0.id")
    Mono<ProductVo> update(UpdateProductDto productDto);
}
