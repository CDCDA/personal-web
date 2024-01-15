package com.pw.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pw.common.entity.BasePageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <博客标签>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_tag")
@ApiModel(value = "博客标签查询类")
public class BlogTagDTO extends BasePageDto {

    @ApiModelProperty(value = "标签名")
    private String tagName;

    @ApiModelProperty(value = "用户id")
    private long userId;

}