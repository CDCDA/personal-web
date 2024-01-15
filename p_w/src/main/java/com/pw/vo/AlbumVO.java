package com.pw.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class AlbumVO implements Serializable {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "相册名")
    private String name;

    @ApiModelProperty(value = "简介")
    private String intro;

    @ApiModelProperty(value = "封面")
    private String coverUrl;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "用户id")
    private long userId;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "图片路径集合")
    @TableField(exist = false)
    private List<ImageVO> images;
}