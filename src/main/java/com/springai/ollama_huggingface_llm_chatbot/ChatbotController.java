package com.springai.ollama_huggingface_llm_chatbot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springai.ollama_huggingface_llm_chatbot.ChatbotService.ChatRequest;
import com.springai.ollama_huggingface_llm_chatbot.ChatbotService.ChatResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Slf4j
@RequestMapping("/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/ask")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest chatRequest) {
        log.info("chatRequest: {}", chatRequest);
        ChatResponse chatResponse = chatbotService.chat(chatRequest);
        log.info("chatResponse: {}", chatResponse);
        return ResponseEntity.ok(chatResponse);
    }
    
    
}
