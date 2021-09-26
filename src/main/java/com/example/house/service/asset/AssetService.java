package com.example.house.service.asset;

import com.example.house.dto.asset.PersonAssetDto;
import com.example.house.entity.Asset;
import com.example.house.entity.Person;

import java.util.List;
import java.util.Map;

public interface AssetService {
    List<PersonAssetDto> findAssetsByPersonId(int person_id);
    void save(Asset asset);
    Person getPersonFromHeader(Map<String, String> header);
    boolean deleteByAssetId(Person person, int asset_id);
}
