package com.huan.demo.mcp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatClient chat;

    public ChatController(ChatClient chat) {
        this.chat = chat;
    }

    public record ChatRequest(String message, String conversationId) {
    }

    @PostMapping
    public Map<String, Object> chat(@RequestBody ChatRequest req) {
        String converId = Optional
                .ofNullable(req.conversationId)
                .orElse(UUID.randomUUID().toString());

        String reply = chat
                .prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, converId))
                .system("""
                        You are a helpful assistant.
                        """)
                .user(req.message())
                .call()
                .content();

        return Map.of("conversationId", converId, "reply", reply);
    }

    @GetMapping
    public Map<String, Object> chatGet(@RequestParam String message,
                                       @RequestParam(defaultValue = "default") String conversationId) {
        String reply = chat.prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .user(message)
                .call()
                .content();
        return Map.of("conversationId", conversationId, "reply", reply);
    }
}
