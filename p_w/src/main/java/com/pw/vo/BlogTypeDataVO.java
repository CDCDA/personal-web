package com.pw.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class BlogTypeDataVO {

    @ApiModelProperty("博客分类")
    private String typeName;

    @ApiModelProperty("博客分类id")
    private String typeId;

    @ApiModelProperty("分类下博客数量")
    private String count;

    @ApiModelProperty("博客列表")
    private List<BlogVO> blogList;
}
