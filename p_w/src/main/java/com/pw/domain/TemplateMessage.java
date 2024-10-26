package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/***
 * @author cyd
 * @date 2024/10/11 13:32
 * @description <>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@ApiModel(value = "微信公众号模板消息")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateMessage {

    @ApiModelProperty(value = "发送对象")
    private String touser;

    @ApiModelProperty(value = "模板id")
    private String template_id;

    @ApiModelProperty(value = "小程序跳转")
    private Map<String,String> miniprogram;

    @ApiModelProperty(value = "参数")
    private Map<String,String>  data;



}
