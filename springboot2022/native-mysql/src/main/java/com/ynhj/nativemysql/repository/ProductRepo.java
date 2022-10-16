package com.ynhj.nativemysql.repository;

import com.ynhj.nativemysql.entiry.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepo extends ReactiveCrudRepository<ProductEntity, Long> {
}
