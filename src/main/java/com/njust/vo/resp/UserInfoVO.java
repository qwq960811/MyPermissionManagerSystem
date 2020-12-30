package com.njust.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/29$ 15:30$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/29$ 15:30$
 * @ Version       :  1.0
 */
@Data
public class UserInfoVO {
    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    @ApiModelProperty(value = "所属机构id")
    private String deptId;
    @ApiModelProperty(value = "所属机构名称")
    private String deptName;
}
