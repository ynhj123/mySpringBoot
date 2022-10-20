package com.ynhj.nativemysql.entiry.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @date: 2022/10/20
 * @author: yangniuhaojiang
 * @title: UpdateProductDto
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@Data
@ApiModel("修改商品")
public class UpdateProductDto extends ProductDto {
    @ApiModelProperty("主键")
    @NotNull
    private Long id;
}
