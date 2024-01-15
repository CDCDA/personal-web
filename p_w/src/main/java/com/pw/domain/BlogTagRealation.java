package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("blog_tag_relation")
@ApiModel(value = "博客标签关联")
public class BlogTagRealation{

    @ApiModelProperty(value = "博客id")
    private Long blogId;

    @ApiModelProperty(value = "标签名")
    private Long tagName;

    @TableId
    @ApiModelProperty(value = "关联id")
    private Long relationId;

}