package com.njust.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/29$ 15:31$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/29$ 15:31$
 * @ Version       :  1.0
 */
@Data
public class PermissionRespNode {
    @ApiModelProperty(value="id")
    private String id;
    @ApiModelProperty(value="菜单名称")
    private String title;
    @ApiModelProperty(value="接口地址")
    private String url;
    private List<?> children;

    @ApiModelProperty(value="是否展开 默认不展开")
    private boolean spread = true;
}
