package com.example.house.dto.mapper;

import com.example.house.dto.asset.PersonAssetDto;
import com.example.house.entity.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AssetMapper {
    AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);

     List<PersonAssetDto> getPersonAssetListDto(List<Asset> assetList);
}
