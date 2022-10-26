package com.ynhj.ingress.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date: 2022/10/19
 * @author: yangniuhaojiang
 * @title: ProductVo
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@ApiModel("商品")
@Data
@NoArgsConstructor
public class ProductVo {
    @ApiModelProperty("主键")
    private long id;
    @ApiModelProperty("商品名")
    private String name;
    @ApiModelProperty("商品描述")
    private String description;
}
