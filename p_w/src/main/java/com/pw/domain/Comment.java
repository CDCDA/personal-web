package com.pw.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.pw.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/***
 * @author cyd
 * @date 2023/5/17 11:35
 * @description <评论>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
@ApiModel(value = "评论")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment extends BaseEntity implements Serializable {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户")
    private Long userId;

    @ApiModelProperty(value = "评论内容")
    private String comment;

    @ApiModelProperty(value = "ip属地")
    private String ipRegion;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "系统")
    private String os;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "父级回复id")
    private String parentId;

    @ApiModelProperty(value = "关联id")
    private String relevanceId;

    @ApiModelProperty(value = "类型:0:博客；1:随笔；2:相册；3:游戏；4:影视；5:网站；")
    private String type;

    @ApiModelProperty(value = "博客网站地址")
    private String webUrl;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "回复评论")
    @TableField(exist = false)
    private List<Comment> children;

}
