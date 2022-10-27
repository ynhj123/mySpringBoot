package com.ynhj.nativemysql.flux;

import com.ynhj.nativemysql.entity.po.ProductPo;
import com.ynhj.nativemysql.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

/**
 * @date: 2022/10/27
 * @author: yangniuhaojiang
 * @title: FluxController
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/flux")
public class FluxController {
    private final ProductService productService;

    @GetMapping("/products")
    public Flux<ProductPo> getAll() {
        return productService.findAllFlux();
    }
}
