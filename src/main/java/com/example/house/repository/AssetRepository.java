package com.example.house.repository;

import com.example.house.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    Asset findByAssetId(int id);
    List<Asset> findAllByPersonId(int person_id);
    Asset save(Asset asset);
    void deleteByAssetId(int asset_id);
}
