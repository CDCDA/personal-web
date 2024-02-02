package com.pw.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pw.common.entity.BasePageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <随笔>
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("album")
@ApiModel(value = "随笔查询类")
public class AlbumDTO extends BasePageDto {
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

    @ApiModelProperty(value = "起始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date startTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "结尾时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}