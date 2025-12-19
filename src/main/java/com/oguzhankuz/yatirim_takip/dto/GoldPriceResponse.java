package com.oguzhankuz.yatirim_takip.dto;

import lombok.Data;
import java.util.List;

@Data
public class GoldPriceResponse {
    private boolean success;
    private List<GoldPriceData> result;

    @Data
    public static class GoldPriceData {
        private String name;
        private Double buying;
        private Double selling;
    }
}