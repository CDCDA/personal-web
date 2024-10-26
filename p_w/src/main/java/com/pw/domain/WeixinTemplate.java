package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @author cyd
 * @date 2024/10/11 13:32
 * @description <>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@ApiModel(value = "微信公众号消息模板")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeixinTemplate {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "模板id")
    private String template_id;

    @ApiModelProperty(value = "模板标题")
    private String title;

    @ApiModelProperty(value = "模板内容")

    private String content;

    @ApiModelProperty(value = "模板样例")
    private String example;

    @ApiModelProperty(value = "")
    private String primary_industry;

    @ApiModelProperty(value = "")
    private String deputy_industry;


}
