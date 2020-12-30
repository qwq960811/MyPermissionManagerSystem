package com.njust.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 21:05$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 21:05$
 * @ Version       :  1.0
 */
@Data
public class UserPageVO {
    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum = 1;
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;
}
