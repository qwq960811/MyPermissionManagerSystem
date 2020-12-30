package com.njust.service.impl;

import com.github.pagehelper.PageHelper;
import com.njust.constants.Constant;
import com.njust.dao.SysUserMapper;
import com.njust.entity.SysUser;
import com.njust.exception.BusinessException;
import com.njust.exception.code.BaseResponseCode;
import com.njust.service.UserService;
import com.njust.utils.JwtTokenUtil;
import com.njust.utils.PageUtil;
import com.njust.utils.PasswordUtils;
import com.njust.vo.req.LoginReqVO;
import com.njust.vo.req.UserPageVO;
import com.njust.vo.resp.LoginRespVO;
import com.njust.vo.resp.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 17:15$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 17:15$
 * @ Version       :  1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public LoginRespVO login(LoginReqVO vo) {
        SysUser sysUser = sysUserMapper.getUserInfoByName(vo.getUsername());
        if(null == sysUser){
            throw new BusinessException(BaseResponseCode.NO_ACCOUNT_ERROR);
        }
        if(sysUser.getStatus() == 2){
            throw new BusinessException(BaseResponseCode.USER_LOCK);
        }
        if(!PasswordUtils.matches(sysUser.getSalt(), vo.getPassword(), sysUser.getPassword())){
            throw new BusinessException(BaseResponseCode.PASSWORD_ERROR);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constant.JWT_ROLES_KEY, getRoleByUserId(sysUser.getId()));
        claims.put(Constant.JWT_PERMISSIONS_KEY, getPermissionByUserId(sysUser.getId()));
        String accessToken = JwtTokenUtil.getAccessToken(sysUser.getId(), claims);
        log.info("accessToken={}", accessToken);

        Map<String, Object> refreshClaims = new HashMap<>();
        refreshClaims.put(Constant.JWT_USER_NAME, sysUser.getUsername());
        String refreshToken = null;
        if(vo.getType().equals("1")){
            refreshToken = JwtTokenUtil.getRefreshToken(sysUser.getId(), refreshClaims);
        } else {
            refreshToken = JwtTokenUtil.getRefreshAppToken(sysUser.getId(), refreshClaims);
        }
        LoginRespVO loginRespVO = new LoginRespVO();
        loginRespVO.setUserId(sysUser.getId());
        loginRespVO.setUsername(sysUser.getUsername());
        loginRespVO.setPhone(sysUser.getPhone());
        loginRespVO.setAccessToken(accessToken);
        loginRespVO.setRefreshToken(refreshToken);


        return loginRespVO;
    }

    /**
     * 分页查询用户信息
     *
     * @param vo
     * @return
     */
    @Override
    public PageVO<SysUser> pageInfo(UserPageVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize()); // UserPageVO只在这里有用过一次吗？
        List<SysUser> sysUsers = sysUserMapper.selectAll(vo);
        return PageUtil.getPageVO(sysUsers);
    }

    private List<String> getRoleByUserId(String userId){
        List<String> list = new ArrayList<>();
        if("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)){
            list.add("admin");
        } else {
            list.add("test");
        }
        return list;
    }

    private List<String> getPermissionByUserId(String userId){
        List<String> list=new ArrayList<>();
        if("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)){
            list.add("sys:user:add");
            list.add("sys:user:list");
            list.add("sys:user:update");
            list.add("sys:user:delete");
        }else{
            list.add("sys:user:add");
        }
        return list;
    }
}
