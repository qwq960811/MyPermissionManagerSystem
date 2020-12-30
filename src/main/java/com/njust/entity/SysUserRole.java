package com.njust.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class SysUserRole implements Serializable {
    private String id;

    private String userId;

    private String roleId;

    private Date createTime;

}