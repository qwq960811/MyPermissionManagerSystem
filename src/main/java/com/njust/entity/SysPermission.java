package com.njust.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class SysPermission implements Serializable {
    private String id;

    private String code;

    private String name;

    private String perms;

    private String url;

    private String method;

    private String pid;

    private Integer orderNum;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    /*父级菜单名称*/
    private String pName;


}