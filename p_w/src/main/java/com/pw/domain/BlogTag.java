package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pw.common.entity.BaseEntity;
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
@ApiModel(value = "博客标签")
public class BlogTag extends BaseEntity {

    @TableId
    @ApiModelProperty(value = "标签id")
    private String tagId;

    @ApiModelProperty(value = "标签名")
    private String tagName;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "标签类型")
    private String tagType;

    @ApiModelProperty(value = "主题")
    private String effec;

    @ApiModelProperty(value = "颜色")
    private String color;





}
