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
 * @description <随笔>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("essay")
@ApiModel(value = "随笔")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Essay extends BaseEntity implements Serializable {
    @TableId
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "用户id")
    private long userId;

    @ApiModelProperty(value = "图片关联")
    @TableField(exist = false)
    private List<ImageRelation> imageRelations;
}