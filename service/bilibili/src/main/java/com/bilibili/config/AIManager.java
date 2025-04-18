/**
 * @ Tool：IntelliJ IDEA
 * @ Author：wkl
 * @ Date：2025-04-16-9:43
 * @ Version：1.0
 * @ Description：
 */

package com.bilibili.config;

import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.ChatCompletionRequest;
import com.zhipu.oapi.service.v4.model.ChatMessage;
import com.zhipu.oapi.service.v4.model.ChatMessageRole;
import com.zhipu.oapi.service.v4.model.ModelApiResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AIManager {
    @Value("${ai.zhipu}")
    private String apiKey;  // 从配置文件中读取API密钥

    private ClientV4 client;

    // 使用@PostConstruct初始化client
    @PostConstruct
    public void init() {
        this.client = new ClientV4.Builder(apiKey).build();
    }

    public String chat(String message) {
        try {
            List<ChatMessage> messages = new ArrayList<>();
            ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), message);

            messages.add(chatMessage);



            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                    .model(Constants.ModelChatGLM4)
                    .stream(Boolean.FALSE)
                    .invokeMethod(Constants.invokeMethod)
                    .messages(messages)
                    .build();

            ModelApiResponse response = client.invokeModelApi(chatCompletionRequest);
            return (String) response.getData().getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            // 处理异常
            throw new RuntimeException("AI对话失败", e);
        }
    }
    // 新增方法：处理图片文件并调用 GLM-4V-Plus 模型
    public String processImageWithGLM4VPlus(String base64Image) {
        try {
            // content 列表：多模态输入（文字 + 图片）
            List<Map<String, Object>> content = new ArrayList<>();

            // 添加文字项
            Map<String, Object> textItem = new HashMap<>();
            textItem.put("type", "text");
            textItem.put("text", "这个图片是什么");
            content.add(textItem);

            // 添加图片项
            Map<String, Object> imageUrl = new HashMap<>();
            imageUrl.put("url", "data:image/jpeg;base64," + base64Image);

            Map<String, Object> imageItem = new HashMap<>();
            imageItem.put("type", "image_url");
            imageItem.put("image_url", imageUrl);

            content.add(imageItem);

            // 构造 ChatMessage，并设置 content
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setRole(ChatMessageRole.USER.value());
            chatMessage.setContent(content);

            List<ChatMessage> messages = new ArrayList<>();
            messages.add(chatMessage);

            // 构造 ChatCompletionRequest
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                    .model("glm-4v-plus")
                    .stream(false)
                    .invokeMethod(Constants.invokeMethod)
                    .messages(messages)
                    .build();

            // 调用智谱 API
            ModelApiResponse response = client.invokeModelApi(chatCompletionRequest);
            return (String) response.getData().getChoices().get(0).getMessage().getContent();

        } catch (Exception e) {
            throw new RuntimeException("调用 GLM-4V-Plus 模型失败", e);
        }
    }

    // 辅助类：用于构造 content 项
    private static class ContentItem {
        private String type;
        private Object value;

        public ContentItem(String type, Object value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public Object getValue() {
            return value;
        }
    }

    // 移除main方法
}

