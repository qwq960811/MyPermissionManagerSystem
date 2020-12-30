package com.njust.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/29$ 15:35$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/29$ 15:35$
 * @ Version       :  1.0
 */
@Data
public class HomeRespVO {
    @ApiModelProperty(value = "用户信息")
    private UserInfoVO userInfo;
    @ApiModelProperty(value = "目录菜单")
    private List<PermissionRespNode> menus;
}
