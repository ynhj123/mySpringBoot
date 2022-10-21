package com.ynhj.nativemysql.reative;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @date: 2022/10/21
 * @author: yangniuhaojiang
 * @title: test
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Slf4j
public class test {
    public static void main(String[] args) {
        String key = "message";
        Mono<String> r = Mono
                .deferContextual(ctx -> Mono.just("Hello " + ctx.get(key)))
                .contextWrite(ctx -> ctx.put(key, "Reactor"))
                .flatMap(s -> Mono.deferContextual(ctx ->
                        Mono.just(s + " " + ctx.get(key))))
                .contextWrite(ctx -> ctx.put(key, "World"))
                .map(s->{
                    System.out.println(s);
                    return s;
                });

        StepVerifier.create(r)
                .expectNext("Hello Reactor World")
                .verifyComplete();
        Mono.deferContextual(ctx -> Mono.just("Hello " + ctx.get(key))).subscribe(System.out::println);
//        String key = "message";
//        Mono<String> r = Mono.just("Hello")
//                .flatMap(s -> Mono
//                        .deferContextual(ctxView -> Mono.just(s + " " + ctxView.get(key)))
//                )
//                .flatMap(s -> Mono
//                        .deferContextual(ctxView -> Mono.just(s + " " + ctxView.get(key)))
//                        .contextWrite(ctx -> ctx.put(key, "Reactor"))
//                )
//                .contextWrite(ctx -> ctx.put(key, "World"));
//
//        StepVerifier.create(r)
//                .expectNext("Hello World Reactor")
//                .verifyComplete();
    }
}
