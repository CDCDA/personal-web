package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pw.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <图片关联>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("image_relation")
@ApiModel(value = "图片关联")
public class ImageRelation {
    @TableId
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "随笔id")
    private long essayId;

    @ApiModelProperty(value = "相册id")
    private long albumId;

    @ApiModelProperty(value = "图片路径")
    private String url;
}