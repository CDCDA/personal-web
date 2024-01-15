package com.pw.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class BlogTypeDTO {

    @TableField
    @ApiModelProperty(value = "分类id")
    private long typeId;

    @ApiModelProperty(value = "父级id")
    private long parentId;

    @ApiModelProperty(value = "分类名")
    private String typeName;

    @ApiModelProperty(value = "用户id")
    private long userId;

}