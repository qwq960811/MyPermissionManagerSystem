package com.njust.service;

import com.njust.entity.SysPermission;
import com.njust.vo.resp.PermissionRespNode;

import java.util.List;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/30$ 20:39$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/30$ 20:39$
 * @ Version       :  1.0
 */
public interface PermissionService {

    List<SysPermission> selectAll();

    /*获取菜单权限树*/
    List<PermissionRespNode> selectAllMenuByTree();
}
