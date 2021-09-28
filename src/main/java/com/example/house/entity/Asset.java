package com.example.house.entity;

import javax.validation.constraints.NotNull;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "asset", schema = "public", catalog = "postgres")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset implements Serializable {
    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "asset_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int assetId;

    @NotNull
    @Column(name = "area")
    private String area;

    @NotNull
    @Column(name = "price")
    private int price;

    @NotNull
    @Column(name = "availability")
    private String availability;

    @NotNull
    @Column(name = "square_meter")
    private int squareMeter;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
    private Person personId;

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