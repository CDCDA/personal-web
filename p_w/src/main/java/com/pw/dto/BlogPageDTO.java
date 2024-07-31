package com.pw.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
 * @description <博客>
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "博客查询类")
public class BlogPageDTO extends BasePageDto {
    @TableField
    @ApiModelProperty("博客id")
    private long blogId;

    @ApiModelProperty(value = "博客标题")
    private String blogTitle;

    @ApiModelProperty(value = "用户id")
    private long userId;

    @ApiModelProperty(value = "分类id")
    private long typeId;

    @ApiModelProperty(value = "标签id")
    private long tagId;

    @ApiModelProperty(value = "封面路径")
    private String coverUrl;

    @ApiModelProperty(value = "博客摘要")
    private String blogAbstract;

    //0：转载，1：原创
    @ApiModelProperty(value = "是否原创")
    private String isOriginal;

    //0：否，1：是
    @ApiModelProperty(value = "是否推荐")
    private String isRecommend;

    @ApiModelProperty(value = "起始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    private Date startTime;

    @TableField(exist = false)
    @ApiModelProperty(value = "结尾时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

}
