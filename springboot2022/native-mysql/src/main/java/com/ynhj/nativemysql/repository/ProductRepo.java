package com.ynhj.nativemysql.repository;

import com.ynhj.nativemysql.entity.po.ProductPo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepo extends ReactiveCrudRepository<ProductPo, Long> {
}
