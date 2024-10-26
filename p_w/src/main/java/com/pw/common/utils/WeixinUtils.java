package com.pw.common.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pw.domain.WeixinTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pw.common.constants.weixinConstants.APPID;
import static com.pw.common.constants.weixinConstants.SECRET;


/***
 * @author cyd
 * @date 2024/10/11 14:28
 * @description <微信公众号相关>
 **/
public class WeixinUtils {

    /**
     * 获取微信公众号token
     *
     * @return
     */
    public static String getToken() {
        String str = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET);
        JSONObject obj = JSONObject.parseObject(str);
        return obj.getString("access_token");
    }

    /**
     * 获取微信公众号消息模板列表
     *
     * @return
     */
    public static List<WeixinTemplate> getTemplateList() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?" +
                "access_token=%s", WeixinUtils.getToken());
        String result = HttpUtil.get(url);
        JSONObject obj = JSONObject.parseObject(result);
        List<WeixinTemplate> templateList = JSON.parseArray(obj.getString("template_list"), WeixinTemplate.class);
        return templateList;
    }

    /**
     * 发送模板消息
     *
     * @return
     */
    public static String sendTemplateMessage(Map<String, Object> dataMap) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?" +
                "access_token=%s", WeixinUtils.getToken());
        String result = HttpUtil.post(url, JSON.toJSONString(dataMap));
        return result;
    }

    /*
       获取文本类型xml字符串
     */
    public static String getTextXml(String toUserName, String fromUserName, String content) {
        String responseXml = "<xml>" +
                "<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>" +  // 发送到原消息的发送者
                "<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>" +  // 您的公众号
                "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[" + content + "]]></Content>" +
                "</xml>";
        return responseXml;
    }

    /*
       解析模板参数
     */
    public static Map<String, String> parseTemplateParams(String content) {
        List<String> tempParams = Arrays.asList(content.split("\n"));
        Pattern pattern = Pattern.compile("\\{\\{(.*?)\\.DATA\\}\\}");
        Map<String, String> params = new HashMap<>();
        for (String tempParam : tempParams) {
            String[] str = tempParam.split(":");
            Matcher matcher = pattern.matcher(str[1]);
            while (matcher.find()) {
                params.put(str[0], matcher.group(1));
            }
        }
        return params;
    }
}
