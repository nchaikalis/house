package com.example.house.service.asset;

import com.example.house.entity.Asset;

import java.util.List;

public interface AssetService {
    List<Asset> findAllByPersonId(int person_id);
    void save(Asset asset);
    boolean deleteByAssetId(int asset_id);
}
