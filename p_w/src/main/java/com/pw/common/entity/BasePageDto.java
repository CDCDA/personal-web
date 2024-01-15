package com.pw.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询基类
 */
@Data
public class BasePageDto implements Serializable {

    @TableField(exist = false)
    @ApiModelProperty(value = "起始页码")
    private Integer pageNum;

    @TableField(exist = false)
    @ApiModelProperty(value = "最大记录数")
    private Integer pageSize;

}