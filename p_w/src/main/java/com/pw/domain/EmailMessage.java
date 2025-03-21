package com.pw.domain;

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
 * @description <邮件>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("eaiil")
@ApiModel(value = "邮件")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailMessage extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "邮箱")
    private String to;

    @ApiModelProperty(value = "内容")
    private String text;

    @ApiModelProperty(value = "主题")
    private String subject;
}
