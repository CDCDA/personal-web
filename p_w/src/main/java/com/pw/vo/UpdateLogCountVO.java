package com.pw.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/***
 * @author cyd
 * @date 2023/7/27 16:01
 * @description <>
 **/
@Data
@ApiModel(value = "更新统计")
public class UpdateLogCountVO {

    @ApiModelProperty(value = "操作更新间")
    @JsonFormat(pattern = "yyyy-MM")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    @ApiModelProperty(value = "博客数量")
    private String total;
}
