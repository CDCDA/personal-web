package com.pw.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * @author cyd
 * @date 2023/7/27 16:01
 * @description <>
 **/
@Data
@ApiModel(value = "博客统计")
public class BlogTagCountVO {
    @ApiModelProperty(value = "标签名")
    private String tagName;

    @ApiModelProperty(value = "博客数量")
    private String total;
}
