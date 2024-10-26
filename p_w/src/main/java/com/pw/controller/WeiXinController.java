package com.pw.controller;

import cn.hutool.core.util.ObjectUtil;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.utils.WeixinUtils;
import com.pw.domain.Message;
import com.pw.domain.WeixinTemplate;
import com.pw.service.UserService;
import com.pw.service.impl.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pw.common.utils.WeixinUtils.*;

@Component
@RestController
@Api(tags = "用户")
@RequestMapping("/pw/weixin")
public class WeiXinController extends BaseController implements convertController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @GetMapping
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


    /**
     * 接收微信服务器发送来的消息
     *
     * @param requestMessage
     * @return
     * @throws Exception
     */
    @PostMapping
    public String receiveMessage(@RequestBody Message requestMessage, HttpServletResponse response) throws IOException {
        String fromUserName = requestMessage.getFromUserName();
        String toUserName = requestMessage.getToUserName();
        String type = requestMessage.getMsgType();
        String content = requestMessage.getContent();
        String responseXml = "";
        switch (type) {
            //文本类型
            case "text":
                //返回用户的openId
                if (content.contains("openId")) {
                    responseXml = WeixinUtils.getTextXml(fromUserName, toUserName, fromUserName);
                }
                //测试模板消息
                else if (content.contains("1")) {
                    List<WeixinTemplate> templateList = getTemplateList();
                    if (templateList.size() == 0) {
                        break;
                    }
                    WeixinTemplate warnTemplate = templateList.stream().filter(x -> "设备异常告警通知".equals(x.getTitle())).findFirst().orElse(null);
                    if (ObjectUtil.isEmpty(warnTemplate)) {
                        break;
                    }
                    Map<String, String> params = parseTemplateParams(warnTemplate.getContent());
                    Map<String, Object> paramMap = new HashMap<>();
                    paramMap.put("touser", fromUserName);
                    paramMap.put("template_id", warnTemplate.getTemplate_id());
                    Map<String, Map<String, String>> dataMap = new HashMap<>();
                    for (String key : params.keySet()) {
                        Map<String, String> map = new HashMap<>();
                        if ("告警时间".equals(key)) {
                            map.put("value", "2024-10-20");

                        } else if ("告警等级".equals(key)) {
                            map.put("value", "1级");
                        } else if ("告警原因".equals(key)) {
                            map.put("value", "硬件故障");
                        }
                        dataMap.put(params.get(key), map);
                    }
                    paramMap.put("data", dataMap);
                    sendTemplateMessage(paramMap);
                }
                break;
            //事件类型
            case "event":
                String event = requestMessage.getEvent();
                //关注事件
                if ("subscribe".equals(event)) {
                    String message = "欢迎关注开铭智能，合作共盈，构建未来！\n" +
                            "【官方网站】http://www.coming-tek.com/zh_cn/index\n" +
                            "【联系电话】400-966-7872\n" +
                            "【官方客服微信】CIT_MES";
                    responseXml = WeixinUtils.getTextXml(fromUserName, toUserName, message);
                }
                break;
            default:
                break;
        }
        return responseXml;
    }
}
