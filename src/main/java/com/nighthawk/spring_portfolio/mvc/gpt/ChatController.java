package com.nighthawk.spring_portfolio.mvc.gpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping
    public String getChatGPTResponse(@RequestBody String prompt) {
        try {
            return chatGPTService.getResponse(prompt);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing request: " + e.getMessage();
        }
    }
}
