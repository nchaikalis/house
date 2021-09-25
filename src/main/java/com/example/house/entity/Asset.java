package com.example.house.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "asset", schema = "public", catalog = "postgres")
@ToString
@Getter
@Setter
public class Asset {
    @Id
    @Column(name = "asset_id")
    private int assetId;

    @Basic
    @Column(name = "area")
    private String area;

    @Basic
    @Column(name = "price")
    private int price;

    @Basic
    @Column(name = "availability")
    private String availability;

    @Basic
    @Column(name = "square_meter")
    private int squareMeter;

    @Basic
    @Column(name = "person_id")
    private Integer personId;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person personByPersonId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return assetId == asset.assetId && price == asset.price && squareMeter == asset.squareMeter && Objects.equals(area, asset.area) && Objects.equals(availability, asset.availability) && Objects.equals(personId, asset.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetId, area, price, availability, squareMeter, personId);
    }
}
