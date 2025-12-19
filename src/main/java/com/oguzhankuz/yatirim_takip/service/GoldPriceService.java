package com.oguzhankuz.yatirim_takip.service;

import com.oguzhankuz.yatirim_takip.dto.GoldPriceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoldPriceService {

    private final String API_URL = "https://api.collectapi.com/economy/goldPrice";

    @Value("${collectapi.key}")
    private String apiKey;

    public Double getCurrent22KGoldPrice() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "apikey " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<GoldPriceResponse> response = restTemplate.exchange(
                    API_URL, HttpMethod.GET, entity, GoldPriceResponse.class);

            if (response.getBody() != null && response.getBody().isSuccess()) {
                return response.getBody().getResult().stream()
                        .filter(gold -> gold.getName().contains("22 Ayar"))
                        .findFirst()
                        .map(GoldPriceResponse.GoldPriceData::getSelling)
                        .orElse(0.0);
            }
        } catch (Exception e) {
            System.err.println("API Hatası: " + e.getMessage());
        }
        return 0.0;
    }
}