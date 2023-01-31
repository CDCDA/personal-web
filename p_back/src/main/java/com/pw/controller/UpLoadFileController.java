package com.pw.controller;

import com.pw.entity.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import static com.pw.controller.BaseController.toAjax;

/*
 * 文件上传与加载
 * */
@CrossOrigin //在服务器端支持跨域访问
@RestController //如果全是ajax请求
@RequestMapping("/file")
public class UpLoadFileController {

//    @Value("${menghui.path}")
    private String basePath = "G:\\";
    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        // file是个临时文件，需要转存到指定路径才能永久保存
        // 截取原始文件名的后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 使用uuid重新生成文件名
        String fileName = UUID.randomUUID().toString() + suffix;
        // 创建一个目录对象
        File dir = new File(basePath);
        if (!dir.exists()) {// 目录若不存在，则创建
            dir.mkdir();
        }

        try {
            System.out.println("开始转存");
            // 将文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

    /**
     * 文件下载
     *
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        try {
            // 输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            // 输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            // 输出的是图片，可以设置一下格式
            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            // 输出结束后关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
