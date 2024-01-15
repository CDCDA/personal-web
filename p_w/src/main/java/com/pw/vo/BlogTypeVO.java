package com.pw.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <博客分类>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_type")
@ApiModel(value = "博客分类")
public class BlogTypeVO {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "分类id")
    private Long typeId;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "分类名")
    private String typeName;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "分类封面")
    private String coverUrl;

    @ApiModelProperty(value = "简介")
    private String intro;

    @ApiModelProperty(value = "分类博客数")
    private Integer total;


}