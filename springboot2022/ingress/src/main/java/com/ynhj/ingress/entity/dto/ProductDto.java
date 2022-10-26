package com.ynhj.ingress.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @date: 2022/10/20
 * @author: yangniuhaojiang
 * @title: ProductDto
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@ApiModel("商品")
@Data
@NoArgsConstructor
public class ProductDto {
    @NotBlank
    @ApiModelProperty(value = "商品名", required = true)
    private String name;
    @ApiModelProperty("商品描述")
    private String des;
}
