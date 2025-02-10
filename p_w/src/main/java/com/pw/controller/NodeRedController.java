package com.pw.controller;

import com.alibaba.fastjson.JSON;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@Api(tags = "node-red物联网对接")
@RequestMapping("/pw/nodeRed")
public class NodeRedController extends BaseController implements convertController {


    @PostMapping("httpTest")
    @ApiOperation(value = "", notes = "", httpMethod = "GET")
    public Result handleValidation(@RequestBody String params) {
        Object obj = JSON.parse(params);
        System.out.println(params);
        return Result.ok(200, "测试成功");
    }

}
