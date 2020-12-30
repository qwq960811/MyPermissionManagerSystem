package com.njust.controller;

import com.njust.entity.SysUser;
import com.njust.service.UserService;
import com.njust.utils.DataResult;
import com.njust.vo.req.LoginReqVO;
import com.njust.vo.req.UserPageVO;
import com.njust.vo.resp.LoginRespVO;
import com.njust.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/24$ 17:11$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/24$ 17:11$
 * @ Version       :  1.0
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户模块相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ApiOperation(value="用户登录")
    public DataResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO vo){
        DataResult<LoginRespVO> result = DataResult.success();
        result.setData(userService.login(vo));
        return result;
    }

    @PostMapping("getAllUser")
    @ApiOperation(value = "分页查询用户接口")
    @RequiresPermissions("sys:user:list")
    public DataResult<PageVO<SysUser>> seletAllUser(@RequestBody UserPageVO vo){
        DataResult result = DataResult.success();
        result.setData(userService.pageInfo(vo));
        return result;
    }
}
