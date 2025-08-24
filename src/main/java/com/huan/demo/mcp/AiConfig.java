package com.huan.demo.mcp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.mcp.AsyncMcpToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    ChatClient chatClient(ChatModel chatModel,
                          ChatMemory chatMemory,
                          AsyncMcpToolCallbackProvider toolCallbackProvider) {

        return ChatClient.builder(chatModel)
                // 1) Gắn Memory Advisor đúng theo docs
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                // 2) (tuỳ dự án của bạn) gắn MCP tools
                .defaultToolCallbacks(toolCallbackProvider.getToolCallbacks())
                .build();
    }

}
