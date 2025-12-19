package com.oguzhankuz.yatirim_takip.controller;

import com.oguzhankuz.yatirim_takip.entity.Asset;
import com.oguzhankuz.yatirim_takip.service.AssetService;
import org.springframework.web.bind.annotation.*;
import com.oguzhankuz.yatirim_takip.dto.PortfolioStatus;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }
    @GetMapping("/status") // GET http://localhost:8080/api/assets/status
    public PortfolioStatus getStatus() {
        return assetService.getPortfolioStatus();
    }

    @GetMapping // GET http://localhost:8080/api/assets
    public List<Asset> getAll() {
        return assetService.getAllAssets();
    }

    @PostMapping // POST http://localhost:8080/api/assets
    public Asset create(@RequestBody Asset asset) {
        return assetService.saveAsset(asset);
    }

}