package com.njust.dao;

import com.njust.entity.SysUser;
import com.njust.vo.req.UserPageVO;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);


    /*根据用户名查询用户信息*/
    SysUser getUserInfoByName(String username);

    /*分页查询所有用户*/
    List<SysUser> selectAll(UserPageVO vo);

}