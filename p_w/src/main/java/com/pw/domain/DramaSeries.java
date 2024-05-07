package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pw.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <影视>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("drama_series")
@ApiModel(value = "影视")
public class DramaSeries extends BaseEntity implements Serializable {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "影视id")
    private Long id;

    @ApiModelProperty(value = "影视分类(0:电影;1:电视剧;2:动漫)")
    private Integer type;

    @ApiModelProperty(value = "封面")
    private String coverUrl;

    @ApiModelProperty(value = "影视名称")
    private String name;

    @ApiModelProperty(value = "简介")
    private String intro;

    @ApiModelProperty(value = "影视播放地址")
    private String url;

    @ApiModelProperty(value = "评分")
    private Float rate;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "用户id")
    private Long user_id;
}
