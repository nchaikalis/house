package com.example.house.service.asset;

import com.example.house.entity.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class DeleteAssetServiceImplTest {
    private final int person_id = 44; // set a correct ID from the DB in order for all tests to work.
    private final int invalidAssetId = -1;

    private Person correctPersonId = new Person();

    @Autowired
    private AssetService assetService;

    @BeforeEach
    public void setMock(){
        correctPersonId.setPersonId(person_id);
    }

    @AfterEach
    public void clearMock(){
        correctPersonId = null;
    }

    @Test
    public void DeleteTestNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            assetService.deleteByAssetId(null, invalidAssetId);
        });
    }

    @Test
    public void DeleteTestZero() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            assetService.deleteByAssetId(new Person(), invalidAssetId);
        });
    }

    @Test
    public void DeleteTestWrongAssetId() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            assetService.deleteByAssetId(correctPersonId, invalidAssetId);
        });
    }
}