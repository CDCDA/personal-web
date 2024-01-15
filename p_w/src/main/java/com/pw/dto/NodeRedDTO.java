package com.pw.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/***
 * @author cyd
 * @date 2023/10/21 14:19
 * @description <>
 **/
@ApiModel(value = "nodered请求信息")
@Data
@ToString
public class NodeRedDTO implements Serializable {


    private MultipartFile KEY;







}

