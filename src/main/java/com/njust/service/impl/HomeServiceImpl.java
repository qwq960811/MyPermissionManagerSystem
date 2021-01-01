package com.njust.service.impl;

import com.alibaba.fastjson.JSON;
import com.njust.dao.SysUserMapper;
import com.njust.entity.SysUser;
import com.njust.service.HomeService;
import com.njust.service.PermissionService;
import com.njust.vo.resp.HomeRespVO;
import com.njust.vo.resp.PermissionRespNode;
import com.njust.vo.resp.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/29$ 15:40$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/29$ 15:40$
 * @ Version       :  1.0
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PermissionService permissionService;

    @Override
    public HomeRespVO getHomeInfo(String userId) {
        HomeRespVO homeRespVO = new HomeRespVO();

        //用户信息
        UserInfoVO userInfoVO = new UserInfoVO();
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId); //获取系统用户信息
        if(!ObjectUtils.isEmpty(sysUser)){
            userInfoVO.setDeptName("NJUST");
            userInfoVO.setId(sysUser.getId());
            userInfoVO.setUsername(sysUser.getUsername());
        }
        homeRespVO.setUserInfo(userInfoVO);

        //菜单信息 (mock假数据)
//        String home="[\n" +
//                "    {\n" +
//                "        \"children\": [\n" +
//                "            {\n" +
//                "                \"children\": [],\n" +
//                "                \"id\": \"3\",\n" +
//                "                \"title\": \"菜单权限管理\",\n" +
//                "                \"url\": \"/index/menus\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"id\": \"1\",\n" +
//                "        \"title\": \"组织管理\",\n" +
//                "        \"url\": \"string\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"children\": [],\n" +
//                "        \"id\": \"2\",\n" +
//                "        \"title\": \"类目2\",\n" +
//                "        \"url\": \"string\"\n" +
//                "    }\n" +
//                "]";
        //List<PermissionRespNode> list = JSON.parseArray(home, PermissionRespNode.class);
        List<PermissionRespNode> list= permissionService.permissionTreeList(userId);

        homeRespVO.setMenus(list);

        return homeRespVO;
    }
}
