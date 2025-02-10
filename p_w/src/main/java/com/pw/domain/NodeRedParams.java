package com.pw.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pw.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <node-red接收参数>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "node-red接收参数")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeRedParams extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "KEY")
    private Map<String,Object> KEY;

    @ApiModelProperty(value = "value")
    private Map<String,Object> value;

    @ApiModelProperty(value = "option")
    private Map<String,Object> option;
}
