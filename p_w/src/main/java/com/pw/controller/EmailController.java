package com.pw.controller;

/***
 * @author cyd
 * @date 2025/2/21 14:59
 * @description <>
 **/

import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.utils.Result;
import com.pw.domain.EmailMessage;
import com.pw.service.impl.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "邮箱")
@RequestMapping("/pw/email")
public class EmailController extends BaseController implements convertController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    @ApiOperation(value = "发送邮件", notes = "", httpMethod = "POST")
    public Result sendEmail(@RequestBody EmailMessage emailMessage) {
        emailService.sendEmail(emailMessage);
        return Result.ok();
    }
}
