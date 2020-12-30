package com.njust.utils;

import com.github.pagehelper.Page;
import com.njust.vo.resp.PageVO;

import java.util.List;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 20:56$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 20:56$
 * @ Version       :  1.0
 */
public class PageUtil {
    private PageUtil(){}

    public static <T> PageVO<T> getPageVO(List<T> list){
        PageVO<T> result = new PageVO<>();

        if(list instanceof Page) {
            Page<T> page = (Page<T>) list;
            result.setTotalRows(page.getTotal()); //总记录数
            result.setTotalPages(page.getPages()); //总页数
            result.setPageNum(page.getPageNum()); //当前第几页
            result.setPageSize(page.getPageSize()); //每页条数
            result.setCurPageSize(page.size()); //当前页记录数
            result.setList(page.getResult());
        }
        return result;
    }
}
