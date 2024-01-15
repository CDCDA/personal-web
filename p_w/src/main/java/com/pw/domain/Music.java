package com.pw.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pw.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <音乐>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("music")
@ApiModel(value = "音乐")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Music extends BaseEntity implements Serializable {
    @TableId
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "音乐名称")
    private String name;

    @ApiModelProperty(value = "封面路径")
    private String coverUrl;

    @ApiModelProperty(value = "音乐路径")
    private String musicUrl;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "简介")
    private String intro;

    @ApiModelProperty(value = "用户id")
    private long userId;

    @ApiModelProperty(value = "用户id")
    private String wyyId;

    @ApiModelProperty(value = "发行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;
}