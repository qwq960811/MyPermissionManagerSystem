package com.njust.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 17:08$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 17:08$
 * @ Version       :  1.0
 */
@Data
public class LoginRespVO {

    @ApiModelProperty(value = "正常的业务token")
    private String accessToken;
    @ApiModelProperty(value = "刷新token")
    private String refreshToken;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "手机")
    private String phone;
}
