package com.springai.ollama_huggingface_llm_chatbot;

import java.util.Optional;
import java.util.UUID;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    private final ChatClient chatClient;
    public record ChatRequest(@Nullable UUID chatId, String question) {}
    public record ChatResponse(UUID chatId, String answer) {}

    public ChatbotService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public ChatResponse chat(ChatRequest chatRequest) {

        UUID chatId = Optional.ofNullable(chatRequest.chatId()).orElse(UUID.randomUUID());

        String answer = chatClient.prompt()
                            .user(chatRequest.question())
                            .advisors(advisorSpec -> advisorSpec.param("chat_memory_conversation_id", chatId))
                            .call()
                            .content();
        
        return new ChatResponse(chatId, answer);
    }
    
}
