package com.ynhj.ingress.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("用户登录")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserVo {
    private String username;
    private String password;
    private String token;
}
