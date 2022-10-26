package com.ynhj.ingress.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("用户登录")
@Data
@NoArgsConstructor
public class LoginUserDto {
    private String username;
    private String password;
}
