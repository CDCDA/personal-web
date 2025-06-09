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
public class BlogTagVO {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "标签id")
    private Long tagId;

    @ApiModelProperty(value = "标签名")
    private String tagName;

    @ApiModelProperty(value = "标签封面")
    private String coverUrl;

    @ApiModelProperty(value = "标签文章数")
    private Integer total;

    @ApiModelProperty(value = "标签类型")
    private String tagType;

    @ApiModelProperty(value = "主题")
    private String effec;

    @ApiModelProperty(value = "颜色")
    private String color;




}
