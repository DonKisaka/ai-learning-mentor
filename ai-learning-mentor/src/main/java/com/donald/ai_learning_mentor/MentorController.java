package com.donald.ai_learning_mentor;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class MentorController {

    private final ChatClient chatClient;

    public MentorController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {
        return chatClient.prompt()
                .advisors(advisor -> advisor
                        .param(ChatMemory.CONVERSATION_ID, request.learnerId()))
                .user(request.message())
                .call()
                .content();
    }

    record ChatRequest(String learnerId, String message) {}
}
