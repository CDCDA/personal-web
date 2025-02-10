package com.pw.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * @author cyd
 * @date 2023/7/27 16:01
 * @description <>
 **/
@Data
@ApiModel(value = "影视分类统计")
public class DramaTypeCountVO {
    @ApiModelProperty(value = "分类")
    private String type;

    @ApiModelProperty(value = "影视数量")
    private String total;
}
