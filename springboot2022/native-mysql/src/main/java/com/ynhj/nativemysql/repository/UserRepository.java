package com.ynhj.nativemysql.repository;

import com.ynhj.nativemysql.entiry.po.UserPo;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserPo, Long> {
    @Query("select * from users where username = :username limit 1")
    Mono<UserPo> findByUsername(String username);
}
