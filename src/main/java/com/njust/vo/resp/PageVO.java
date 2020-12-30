package com.njust.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 20:54$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 20:54$
 * @ Version       :  1.0
 */
@Data
public class PageVO<T> {
    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long totalRows;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Integer totalPages;
    /**
     * 当前第几页
     */
    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;
    /**
     * 当前页记录数
     */
    @ApiModelProperty(value = "当前页记录数")
    private Integer curPageSize;
    /**
     * 数据列表
     */
    @ApiModelProperty(value = "数据列表")
    private List<T> list;
}
