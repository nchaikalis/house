package com.example.house.service.asset;

import com.example.house.entity.Asset;

import java.util.List;
import java.util.Map;

public interface AssetService {
    List<Asset> findAllByPersonId(int person_id);
    void save(Asset asset);
    String getToken( Map<String, String> header);
    boolean deleteByAssetId(int asset_id);
}
