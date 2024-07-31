package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
import java.util.List;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <相册>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("album")
@ApiModel(value = "相册")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Album extends BaseEntity implements Serializable {
    @TableId
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "相册名")
    private String name;

    @ApiModelProperty(value = "简介")
    private String intro;

    @ApiModelProperty(value = "封面")
    private String coverUrl;

    @ApiModelProperty(value = "用户id")
    private long userId;

    @ApiModelProperty(value = "图片关联")
    @TableField(exist = false)
    private List<ImageRelation> images;
}
