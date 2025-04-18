package com.bilibili.controller;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.bilibili.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

@Controller
@Slf4j
public class ImageClassifyController {

    @Autowired
    private AipImageClassify aipImageClassify;



    @PostMapping("/classify")
    @ResponseBody
    public Result classifyImage(@RequestParam("file") MultipartFile file) throws IOException {
        // 创建AipImageClassify实例，自动处理access_token

        // 验证文件大小（最大4MB）
        if (file.getSize() > 4 * 1024 * 1024) {
            return Result.ok("文件大小超过4MB");
        }

        // 将文件转换为Base64编码
        byte[] fileBytes = file.getBytes();
        String base64String = java.util.Base64.getEncoder().encodeToString(fileBytes);

        // 验证图片宽高、长宽比等
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(fileBytes));
        int width = image.getWidth();
        int height = image.getHeight();

// ... existing code ...
// 检查图片的最短边和最长边
if (width < 15 || height < 15) {
    return Result.fail("图片的最短边小于15px");
}
if (width > 4096 || height > 4096) {
    return Result.fail("图片的最长边超过4096px");
}

// 检查长宽比
double aspectRatio = (double) width / height;
if (aspectRatio > 3 || aspectRatio < 1.0 / 3) {
    return Result.fail("图片长宽比超过3:1");
}
// ... existing code ...

        byte[] bytes = file.getBytes();

// 创建options参数
        HashMap<String, String> options = new HashMap<>();
        options.put("baike_num", "5"); // 设置返回百科信息的数量
        log.info(aipImageClassify.getClass().toString());

        // 调用百度AI识别接口（自动获取access_token）
        JSONObject planted = aipImageClassify.plantDetect(bytes, options);

        return Result.ok(planted.toMap());  // 返回JSON结果
    }
}
