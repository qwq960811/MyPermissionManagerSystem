package com.njust.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 14:49$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 14:49$
 * @ Version       :  1.0
 */
@Data
public class TestReqVO {

    /**
     * @NotEmpty 用在集合类上面
     * @NotBlank 用在String上面
     * @NotNull 用在基本数据类型上
     * @Valid:启用校验
     */

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ApiModelProperty(value = "id集合")
    @NotEmpty(message = "id集合不能为空")
    private List<String> ids;


}
