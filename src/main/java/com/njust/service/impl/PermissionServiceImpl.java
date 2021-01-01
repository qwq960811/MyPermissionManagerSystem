package com.njust.service.impl;

import com.njust.dao.SysPermissionMapper;
import com.njust.entity.SysPermission;
import com.njust.exception.BusinessException;
import com.njust.exception.code.BaseResponseCode;
import com.njust.service.PermissionService;
import com.njust.vo.req.PermissionAddReqVO;
import com.njust.vo.resp.PermissionRespNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/30$ 20:40$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/30$ 20:40$
 * @ Version       :  1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private SysPermissionMapper SysPermissionMapper;

    @Override
    public List<SysPermission> selectAll() {
        List<SysPermission> sysPermissions = SysPermissionMapper.selectAll();
        if(!sysPermissions.isEmpty()){
            for (SysPermission sysPermission : sysPermissions) {
                SysPermission parent = SysPermissionMapper.selectByPrimaryKey(sysPermission.getPid());
                if(parent!=null){
                    sysPermission.setPName(parent.getName());
                }
            }
        }

        return sysPermissions;
    }

    @Override
    public List<PermissionRespNode> selectAllMenuByTree() {
        List<SysPermission> sysPermissions = SysPermissionMapper.selectAll();
        List<PermissionRespNode> list = new ArrayList<>();

        PermissionRespNode permissionRespNode = new PermissionRespNode();
        permissionRespNode.setId("0");
        permissionRespNode.setTitle("默认顶级菜单");
        permissionRespNode.setChildren(getTree(sysPermissions));
        list.add(permissionRespNode);
        return list;
        //return getTree(SysPermissionMapper.selectAll());
    }

    @Override
    public SysPermission addPermission(PermissionAddReqVO vo) {
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(vo,sysPermission);
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermission.setCreateTime(new Date());
        int count = SysPermissionMapper.insertSelective(sysPermission);
        if(count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return sysPermission;
    }

    @Override
    public List<PermissionRespNode> permissionTreeList(String userId) {
        return getTree(selectAll());
    }

    private void verifyForm(SysPermission sysPermission){
        SysPermission parent = SysPermissionMapper.selectByPrimaryKey(sysPermission.getPid());
        switch(sysPermission.getType()){
            case 1:
                if(parent!=null){
                    if(parent.getType()!=1){
                        throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                    }
                } else if(!sysPermission.getPid().equals("0")){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                }
                break;
            case 2:
                if(parent==null||parent.getType()!=1){
                    throw new
                            BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_MENU_ERROR);
                }
                if(StringUtils.isEmpty(sysPermission.getUrl())){
                    throw new
                            BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                break;
            case 3:
                if(parent==null||parent.getType()!=2){
                    throw new
                            BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_BTN_ERROR);
                }
                if(StringUtils.isEmpty(sysPermission.getPerms())){
                    throw new
                            BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_PERMS_NULL);
                }
                if(StringUtils.isEmpty(sysPermission.getUrl())){
                    throw new
                            BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                if(StringUtils.isEmpty(sysPermission.getMethod())){
                    throw new
                            BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
                }

                break;
        }
    }


    private List<PermissionRespNode> getTree(List<SysPermission> sysPermissions) {

        List<PermissionRespNode> list = new ArrayList<>();
        if(sysPermissions.isEmpty()){
            return list;
        }
        for(SysPermission sysPermission : sysPermissions){
            if("0".equals(sysPermission.getPid())){
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());

                permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(),sysPermissions));

                list.add(permissionRespNode);
            }
        }
        return list;
    }

    /**
     * 递归获取菜单和目录
     * @param id
     * @param sysPermissions
     * @return
     */
    private List<PermissionRespNode> getChildExcBtn(String id, List<SysPermission> sysPermissions) {
        List<PermissionRespNode> list = new ArrayList<>();
        for(SysPermission sysPermission : sysPermissions){
            if(sysPermission.getPid().equals(id) && sysPermission.getType() != 3){
                PermissionRespNode permissionRespNode = new PermissionRespNode();
                BeanUtils.copyProperties(sysPermission, permissionRespNode);
                permissionRespNode.setTitle(sysPermission.getName());
                permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(),sysPermissions));
                list.add(permissionRespNode);
            }
        }

        return list;
    }
}
