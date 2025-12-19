package com.oguzhankuz.yatirim_takip.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortfolioStatus {
    private Double totalAmount;
    private Double totalCost;
    private Double currentValuation;
    private Double profitLoss;
    private Double currentGoldPrice;
}