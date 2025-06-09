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
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //    @TableField(exist = false)
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者")
    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    @TableField(fill = FieldFill.UPDATE)
//    @TableField(exist = false)
    private String updateBy;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime ;

    @TableField(exist = false)
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField(exist = false)
    @ApiModelProperty(value = "起始页码")
    private Integer pageNum;

    @TableField(exist = false)
    @ApiModelProperty(value = "最大记录数")
    private Integer pageSize;

    @TableField(exist = false)
    @ApiModelProperty(value = "排序字段")
    private String orderBy;

    @TableField(exist = false)
    @ApiModelProperty(value = "排序方式")
    private String asc;


    /**
     * 初始化实体
     *
     * @param createdUserId
     */
    public void initialize(final String createdUserId) {
        this.setCreateTime(new Date());
        this.setUpdateTime(new Date());
        this.setCreateBy(createdUserId);
        this.setUpdateBy(createdUserId);
    }

    /**
     * 变更实体
     *
     * @param updateUserId
     */
    public void modify(final String updateUserId) {
        this.setUpdateTime(new Date());
        this.setUpdateBy(updateUserId);
    }

}
