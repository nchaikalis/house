package com.example.house.dto.asset;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonAssetDto {
    private int assetId;
    private String area;
    private int price;
    private String availability;
    private int squareMeter;

    public PersonAssetDto() {
    }

    public PersonAssetDto(int assetId, String area, int price, String availability, int squareMeter) {
        this.assetId = assetId;
        this.area = area;
        this.price = price;
        this.availability = availability;
        this.squareMeter = squareMeter;
    }
}
