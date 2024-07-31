package com.pw.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/***
 * @author cyd
 * @date 2023/5/24 17:59
 * @description <>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog")
@ApiModel(value = "博客")
public class BlogVO {
    @TableField
    @ApiModelProperty("博客id")
    private String blogId;

    @ApiModelProperty(value = "博客标题")
    private String blogTitle;

    @ApiModelProperty(value = "博客内容")
    private String content;

    @ApiModelProperty(value = "博客markdown内容")
    private String mkContent;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "分类id")
    private String typeId;

    @ApiModelProperty(value = "分类")
    private String typeName;

    @ApiModelProperty(value = "封面路径")
    private String coverUrl;

    @ApiModelProperty(value = "博客摘要")
    private String blogAbstract;

    //0：转载，1：原创
    @ApiModelProperty(value = "是否原创")
    private String isOriginal;

    @ApiModelProperty(value = "作者")
    private String author;

    //0：否，1：是
    @ApiModelProperty(value = "是否推荐")
    private String isRecommend;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "标签列表")
    @TableField(exist = false)
    private List<BlogTagVO> tags;

}
