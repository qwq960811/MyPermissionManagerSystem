package com.njust.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 17:06$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 17:06$
 * @ Version       :  1.0
 */
@Data
public class LoginReqVO {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "登录类型（1:web, 2:app）")
    @NotBlank(message = "登录类型不能为空")
    private String type;
}
