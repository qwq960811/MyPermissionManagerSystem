package com.njust.service;

import com.njust.utils.DataResult;
import com.njust.vo.resp.HomeRespVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/29$ 15:39$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/29$ 15:39$
 * @ Version       :  1.0
 */
public interface HomeService {
    HomeRespVO getHomeInfo(String userId);


}
