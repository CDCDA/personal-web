package com.pw.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类
 */
@Data
public class PageEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    @ApiModelProperty(value = "起始页码")
    private Integer pageNum;

    @TableField(exist = false)
    @ApiModelProperty(value = "最大记录数")
    private Integer pageSize;

}