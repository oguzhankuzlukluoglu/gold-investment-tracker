package com.oguzhankuz.yatirim_takip.service;

import com.oguzhankuz.yatirim_takip.dto.PortfolioStatus;
import com.oguzhankuz.yatirim_takip.entity.Asset;
import com.oguzhankuz.yatirim_takip.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final GoldPriceService goldPriceService;

    public AssetService(AssetRepository assetRepository, GoldPriceService goldPriceService) {
        this.assetRepository = assetRepository;
        this.goldPriceService = goldPriceService;
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }


    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    public PortfolioStatus getPortfolioStatus() {
        List<Asset> assets = assetRepository.findAll();

        Double currentPrice = goldPriceService.getCurrent22KGoldPrice();

        Double totalAmount = assets.stream()
                .mapToDouble(Asset::getAmount)
                .sum();

        Double totalCost = assets.stream()
                .mapToDouble(a -> a.getAmount() * a.getBuyPrice())
                .sum();

        Double currentValuation = totalAmount * currentPrice;

        return PortfolioStatus.builder()
                .totalAmount(totalAmount)
                .totalCost(totalCost)
                .currentValuation(currentValuation)
                .profitLoss(currentValuation - totalCost)
                .currentGoldPrice(currentPrice)
                .build();
    }
}