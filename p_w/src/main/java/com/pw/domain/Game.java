package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * @description <游戏>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("game")
@ApiModel(value = "游戏")
public class Game extends BaseEntity implements Serializable {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "游戏id")
    private Long id;

    @ApiModelProperty(value = "游戏类型(0:单机;1:手游)")
    private String type;

    @ApiModelProperty(value = "封面")
    private String coverUrl;

    @ApiModelProperty(value = "游戏名称")
    private String name;

    @ApiModelProperty(value = "简介")
    private String intro;

    @ApiModelProperty(value = "游戏官网")
    private String url;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "用户id")
    private Long user_id;
}