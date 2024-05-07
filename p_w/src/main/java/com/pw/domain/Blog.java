package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pw.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <博客>
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog")
@ApiModel(value = "博客")
public class Blog extends BaseEntity {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("博客id")
    private Long blogId;

    @ApiModelProperty(value = "博客标题")
    private String blogTitle;

    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "博客markdown内容")
    private String mkContent;

    @ApiModelProperty(value = "用户id")
    private long userId;

    @ApiModelProperty(value = "分类id")
    private long typeId;

    @ApiModelProperty(value = "封面路径")
    private String coverUrl;

    @ApiModelProperty(value = "博客摘要")
    private String blogAbstract;

    //0：转载，1：原创
    @ApiModelProperty(value = "是否原创")
    private String isOriginal;

    @ApiModelProperty(value = "标签列表")
    @TableField(exist = false)
    private List<BlogTag> tags;
}
