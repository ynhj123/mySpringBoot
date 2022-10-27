package com.ynhj.nativemysql.service;

import com.ynhj.nativemysql.entity.dto.ProductDto;
import com.ynhj.nativemysql.entity.dto.UpdateProductDto;
import com.ynhj.nativemysql.entity.po.ProductPo;
import com.ynhj.nativemysql.entity.vo.ProductVo;
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
    Flux<ProductVo> findAll(Long pageSize, Long pageNum);

    @Cacheable(value = "product-list-flux")
    Flux<ProductPo> findAllFlux();

    @Cacheable(value = "product-id", key = "#p0")
    Mono<ProductVo> findById(Long id);

    @CacheEvict(cacheNames = {"product-list", "product-list-flux"}, allEntries = true)
    Mono<ProductVo> insert(ProductDto productDto);

    @CacheEvict(cacheNames = {"product-list", "product-id", "product-list-flux"}, allEntries = true)
    Mono<ProductVo> update(UpdateProductDto productDto);

    @CacheEvict(cacheNames = {"product-list", "product-id", "product-list-flux"}, allEntries = true)
    Mono<Void> delete(Long id);
}
