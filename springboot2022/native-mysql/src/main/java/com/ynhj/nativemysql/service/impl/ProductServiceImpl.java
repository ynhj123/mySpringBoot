package com.ynhj.nativemysql.service.impl;

import com.ynhj.nativemysql.common.entity.GlobalException;
import com.ynhj.nativemysql.entiry.po.ProductPo;
import com.ynhj.nativemysql.entiry.dto.ProductDto;
import com.ynhj.nativemysql.entiry.dto.UpdateProductDto;
import com.ynhj.nativemysql.entiry.vo.ProductVo;
import com.ynhj.nativemysql.repository.ProductRepo;
import com.ynhj.nativemysql.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public Flux<ProductVo> findAll(Long pageSize, Long pageNum) {
        return productRepo.findAll().skip(pageNum * pageSize).take(pageSize).map(this::wrapper).cache();
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

    @Override
    public Mono<ProductVo> insert(ProductDto productDto) {
        ProductPo productPo = new ProductPo();
        productPo.setDescription(productDto.getName());
        productPo.setName(productDto.getName());
        return productRepo.save(productPo).map(this::wrapper);
    }

    @Override
    public Mono<ProductVo> update(UpdateProductDto productDto) {
        return productRepo.findById(productDto.getId())
                .switchIfEmpty(Mono.error(new GlobalException(HttpStatus.NOT_FOUND)))
                .flatMap(productPo -> {
                    productPo.setName(productDto.getName());
                    productPo.setDescription(productDto.getDes());
                    return productRepo.save(productPo);
                }).map(this::wrapper);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return productRepo.deleteById(id).switchIfEmpty(Mono.error(new GlobalException(HttpStatus.NOT_FOUND)));
    }
}
