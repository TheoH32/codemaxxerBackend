package com.nighthawk.spring_portfolio.mvc.gpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatGPTService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final String apiUrl = "https://api.openai.com/v1/completions";

    public String getResponse(String prompt) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("model", "gpt-3.5-turbo");
        requestBodyMap.put("prompt", prompt);
        requestBodyMap.put("max_tokens", 150);

        String requestBody = objectMapper.writeValueAsString(requestBodyMap);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            return restTemplate.postForObject(apiUrl, entity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get response from OpenAI: " + e.getMessage());
        }
    }
}
