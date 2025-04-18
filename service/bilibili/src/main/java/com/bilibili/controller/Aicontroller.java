/**
 * @ Tool：IntelliJ IDEA
 * @ Author：wkl
 * @ Date：2025-04-17-21:14
 * @ Version：1.0
 * @ Description：ai调用
 */

package com.bilibili.controller;
// ... existing imports ...
import com.bilibili.config.AIManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RequestMapping("ai")
@RestController
public class Aicontroller {
    @Autowired
    private AIManager aiManager;

    @PostMapping("/chat")
    public String chat(@RequestParam String message) {
        return aiManager.chat(message);
    }

    @PostMapping("/process-image")
    public String processImage(@RequestParam("file") MultipartFile file) throws IOException {
        String base64 = Base64.getEncoder().encodeToString(file.getBytes());

            String result = aiManager.processImageWithGLM4VPlus(base64);
        return result;
    }
}
