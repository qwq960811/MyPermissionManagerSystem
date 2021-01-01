package com.njust.controller;

import com.njust.entity.SysPermission;
import com.njust.service.PermissionService;
import com.njust.utils.DataResult;
import com.njust.vo.req.PermissionAddReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/30$ 20:49$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/30$ 20:49$
 * @ Version       :  1.0
 */
@RestController
@RequestMapping("api")
@Api(tags="组织模块-菜单权限管理")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("permissions")
    @ApiOperation(value="获取所有菜单权限接口")
    public DataResult<List<SysPermission>> getAllMenusPermission(){
        DataResult result = DataResult.success();
        result.setData(permissionService.selectAll());
        return result;
    }

    @GetMapping("permission/tree")
    @ApiOperation(value="获取所有目录菜单树接口-查到到目录")
    public DataResult<List<SysPermission>> getAllMenusPermissionTree(){
        DataResult result = DataResult.success();
        result.setData(permissionService.selectAllMenuByTree());
        return result;
    }

    @PostMapping("permission")
    @ApiOperation(value="新增菜单权限接口")
    public DataResult<SysPermission> addPermission(@RequestBody @Valid PermissionAddReqVO vo){
        DataResult result = DataResult.success();
        result.setData(permissionService.addPermission(vo));
        return result;
    }
}
