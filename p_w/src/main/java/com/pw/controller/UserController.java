package com.pw.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.token.UserLoginToken;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.domain.User;
import com.pw.service.UserService;
import com.pw.service.impl.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static com.pw.common.utils.convertWrapper.convertEqWrap;
import static com.pw.common.utils.convertWrapper.convertWrap;
import static com.pw.common.utils.pageUtil.setPage;

@Component
@RestController
@Api(tags = "用户")
@RequestMapping("/pw/user")
public class UserController extends BaseController implements convertController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //    @UserLoginToken
    @GetMapping("/list")
    @ApiOperation(value = "用户列表分页查询", notes = "", httpMethod = "GET")
    public Result list(User user) {
        redisTemplate.opsForValue().set("AAA", "aaa");
        System.out.println("REDIS" + redisTemplate.opsForValue().get("AAA").toString());
        IPage<User> result = userService.page(setPage(user), convertWrap(user));
        return resultIPage(result);
    }

    @UserLoginToken(required = true)
    @PostMapping("/save")
    @ApiOperation(value = "新增或修改用户", notes = "", httpMethod = "POST")
    public Result save(@RequestBody User user) {
        if (ObjectUtils.isNotEmpty(user.getUserId())) {
            return resultExit(userService.updateById(user));
        }
        user.setUserId(new SnowFlake(1, 0).nextId());
        return resultExit(userService.save(user));
    }


    @PostMapping("/delete")
    @ApiOperation(value = "删除用户", notes = "", httpMethod = "POST")
    public Result delete(String userId) {
        if (userId != null && !userId.equals("")) {
            return resultExit(userService.removeById(userId));
        }
        return Result.error();
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除用户", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(userService.removeByIds(ids));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "用户详情", notes = "", httpMethod = "GET")
    public Result deleteByIds(@PathVariable String id) {
        return resultData(userService.getById(id));
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public Result login(@RequestBody User user, HttpServletResponse response) {
        User result = userService.getOne(convertEqWrap(user));
        if (result != null) {
            String token = tokenService.getToken(result);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return Result.ok().data("token", token);
        }
        return resultExit(result);
    }

    @GetMapping("/tokenVerify")
    @UserLoginToken
    @ApiOperation(value = "token验证", notes = "", httpMethod = "GET")
    public Result tokenVerify(User user, HttpServletResponse response) {
        return Result.ok();
    }

    @GetMapping("/touristLogIn")
    @ApiOperation(value = "游客登录", notes = "", httpMethod = "GET")
    public Result touristLogIn(HttpServletResponse response) {
        String token = tokenService.getTouristToken();
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        return Result.ok().data("token", token);
    }


    @GetMapping("/weixinVerify")
    @ApiOperation(value = "微信公众号校验", notes = "", httpMethod = "GET")
    public void handleValidation(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam("signature") String signature,
                                 @RequestParam("timestamp") String timestamp,
                                 @RequestParam("nonce") String nonce) throws IOException {
        // 微信公众号的 token（自己设置）
        String token = "yourToken";

        // 按照微信要求进行验证计算
        String[] params = {token, timestamp, nonce};
        Arrays.sort(params);
        StringBuffer sb = new StringBuffer();
        for (String param : params) {
            sb.append(param);
        }
        String sha1 = calculateSHA1(sb.toString());

        if (sha1.equals(signature)) {
            // 验证通过，返回指定响应
            response.getWriter().write(request.getParameter("echostr"));
        } else {
            response.setStatus(400);
        }
    }

    // 计算 SHA1
    private String calculateSHA1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(input.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
