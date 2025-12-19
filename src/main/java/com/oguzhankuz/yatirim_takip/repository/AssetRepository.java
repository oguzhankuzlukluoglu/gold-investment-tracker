package com.oguzhankuz.yatirim_takip.repository;

import com.oguzhankuz.yatirim_takip.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

}