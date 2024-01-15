package com.pw.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/***
 * @author cyd
 * @date 2023/5/24 17:59
 * @description <更新日志>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("update_log")
@ApiModel(value = "更新日志")
public class UpdateLogVO {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "更新操作")
    private String operation;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}