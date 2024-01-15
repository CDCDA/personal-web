package com.pw.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pw.common.entity.BasePageDto;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class UserDTO extends BasePageDto {
    @TableField
    @ApiModelProperty(value = "用户主键")
    private long userId;

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
}