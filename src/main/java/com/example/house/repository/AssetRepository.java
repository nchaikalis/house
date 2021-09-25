package com.example.house.repository;

import com.example.house.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    Asset findByAssetId(int id);
    Asset save(Asset asset);
    void deleteByAssetId(int asset_id);

    @Query(nativeQuery = true, value = "SELECT * FROM asset WHERE person_id = ?1")
    List<Asset> findAssetsByPersonId(int id);

}
