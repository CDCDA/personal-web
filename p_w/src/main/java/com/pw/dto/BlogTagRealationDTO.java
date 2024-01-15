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
 * @date 2023/5/17 14:05
 * @description <标签博客关联>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_type")
@ApiModel(value = "博客标签关联")
public class BlogTagRealationDTO {

    @TableField

    private long blogId;

    @ApiModelProperty(value = "标签id")
    private long tagId;

    @ApiModelProperty(value = "关联id")
    private long relationId;

}