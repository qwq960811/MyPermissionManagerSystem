package com.njust.service;

import com.njust.entity.SysUser;
import com.njust.utils.DataResult;
import com.njust.vo.req.LoginReqVO;
import com.njust.vo.req.UserPageVO;
import com.njust.vo.resp.LoginRespVO;
import com.njust.vo.resp.PageVO;


/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 17:14$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 17:14$
 * @ Version       :  1.0
 */
public interface UserService {
    LoginRespVO login(LoginReqVO vo);


    /**
     * 分页查询用户信息
     * @param vo
     * @return
     */
    PageVO<SysUser> pageInfo(UserPageVO vo);
}
