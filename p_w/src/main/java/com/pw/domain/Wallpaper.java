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
 * @description <壁纸>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("wallpaper")
@ApiModel(value = "壁纸")
public class Wallpaper extends BaseEntity implements Serializable {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "壁纸id")
    private Long id;

    @ApiModelProperty(value = "壁纸类型(0:静态;1:动态)")
    private String type;

    @ApiModelProperty(value = "壁纸路径")
    private String url;

    @ApiModelProperty(value = "动态壁纸封面")
    private String coverUrl;

    @ApiModelProperty(value = "壁纸名称")
    private String name;
}