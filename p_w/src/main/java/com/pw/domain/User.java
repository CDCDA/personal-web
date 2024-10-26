package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pw.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <用户>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@ApiModel(value = "用户")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity implements Serializable {
    @TableId
    @ApiModelProperty(value = "用户主键")
    private Long userId;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "个人介绍")
    private String introduction;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "博客网站")
    private String webUrl;

    @ApiModelProperty(value = "ip属地")
    private String ipRegion;

    @ApiModelProperty(value = "系统")
    private String os;

    @ApiModelProperty(value = "浏览器")
    private String browser;
}
