package com.example.house.service.asset;

import com.example.house.entity.Asset;
import com.example.house.entity.Person;

import java.util.List;
import java.util.Map;

public interface AssetService {
    List<Asset> findAssetsByPersonId(int person_id);
    void save(Asset asset);
    Person getPersonFromHeader(Map<String, String> header);
    boolean deleteByAssetId(int asset_id);
}
