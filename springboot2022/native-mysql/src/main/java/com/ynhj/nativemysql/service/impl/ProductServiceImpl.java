package com.ynhj.nativemysql.service.impl;

import com.ynhj.nativemysql.entiry.ProductPo;
import com.ynhj.nativemysql.entiry.vo.ProductVo;
import com.ynhj.nativemysql.repository.ProductRepo;
import com.ynhj.nativemysql.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    private final ProductRepo productRepo;

    @Override
    public Flux<ProductVo> findAll() {
        return productRepo.findAll().map(this::wrapper).cache();
    }

    @Override
    public Mono<ProductVo> findById(Long id) {
        return productRepo.findById(id).map(this::wrapper).cache();
    }

    ProductVo wrapper(ProductPo productPo) {
        ProductVo productVo = new ProductVo();
        productVo.setId(productPo.getId());
        productVo.setName(productPo.getName());
        productVo.setDescription(productPo.getDescription());
        return productVo;
    }
}
