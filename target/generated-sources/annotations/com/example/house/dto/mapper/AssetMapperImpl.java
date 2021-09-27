package com.example.house.dto.mapper;

import com.example.house.dto.asset.PersonAssetDto;
import com.example.house.entity.Asset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-27T22:01:08+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
public class AssetMapperImpl implements AssetMapper {

    @Override
    public List<PersonAssetDto> getPersonAssetListDto(List<Asset> assetList) {
        if ( assetList == null ) {
            return null;
        }

        List<PersonAssetDto> list = new ArrayList<PersonAssetDto>( assetList.size() );
        for ( Asset asset : assetList ) {
            list.add( assetToPersonAssetDto( asset ) );
        }

        return list;
    }

    protected PersonAssetDto assetToPersonAssetDto(Asset asset) {
        if ( asset == null ) {
            return null;
        }

        PersonAssetDto personAssetDto = new PersonAssetDto();

        personAssetDto.setAssetId( asset.getAssetId() );
        personAssetDto.setArea( asset.getArea() );
        personAssetDto.setPrice( asset.getPrice() );
        personAssetDto.setAvailability( asset.getAvailability() );
        personAssetDto.setSquareMeter( asset.getSquareMeter() );

        return personAssetDto;
    }
}
