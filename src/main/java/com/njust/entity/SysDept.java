package com.njust.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class SysDept implements Serializable {
    private String id;

    private String deptNo;

    private String name;

    private String pid;

    private Integer status;

    private String relationCode;

    private String deptManagerId;

    private String managerName;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;


}