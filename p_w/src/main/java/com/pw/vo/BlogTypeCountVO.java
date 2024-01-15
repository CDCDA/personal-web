package com.pw.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/***
 * @author cyd
 * @date 2023/7/27 16:01
 * @description <>
 **/
@Data
@ApiModel(value = "博客统计")
public class BlogTypeCountVO {
    @ApiModelProperty(value = "分类名")
    private String typeName;

    @ApiModelProperty(value = "博客数量")
    private String total;
}
