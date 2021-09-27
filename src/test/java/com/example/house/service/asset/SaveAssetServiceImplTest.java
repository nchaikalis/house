package com.example.house.service.asset;

import com.example.house.entity.Asset;
import com.example.house.entity.Person;
import com.example.house.exception.asset.AssetSaveException;
import com.example.house.exception.person.PersonSearchException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SaveAssetServiceImplTest {
    private final int person_id = 187; // set a correct ID from the DB in order for all tests to work
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
    public void SaveTestNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            assetService.save(null, null);
        });
    }

    @Test
    public void SaveTestZero() {
        Asset asset = new Asset();
        Person person = new Person();
        Assertions.assertThrows(PersonSearchException.class, () -> {
            assetService.save(asset, person);
        });
    }

    @Test
    public void SaveTestWrongPersonId() {
        Asset asset = new Asset(1, "Αθήνα", 10000, "Πώληση", 40, new Person());
        Assertions.assertThrows(PersonSearchException.class, () -> {
            assetService.save(asset, new Person());
        });
    }

    @Test
    public void SaveTestWrongSquareMeterMin() {
        Asset asset = new Asset(1, "Αθήνα", 10000, "Πώληση", 19, correctPersonId);
        Assertions.assertThrows(AssetSaveException.class, () -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestWrongSquareMeterMax() {
        Asset asset = new Asset(1, "Αθήνα", 10000, "Πώληση", 1001, correctPersonId);
        Assertions.assertThrows(AssetSaveException.class, () -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestWrongAvailability() {
        Asset assetError = new Asset(1, "Αθήνα", 10000, "Χάρισμα", 500, correctPersonId);
        Assertions.assertThrows(AssetSaveException.class, () -> {
            assetService.save(assetError, correctPersonId);
        });
    }

    @Test
    public void SaveTestWrongPriceMin() {
        Asset asset = new Asset(1, "Αθήνα", 49, "Πώληση", 500, correctPersonId);
        Assertions.assertThrows(AssetSaveException.class, () -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestWrongPriceMax() {
        Asset asset = new Asset(1, "Αθήνα", 5000001, "Πώληση", 500, correctPersonId);
        Assertions.assertThrows(AssetSaveException.class, () -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestWrongWrongCity() {
        Asset asset = new Asset(1, "Ζάκυνθος", 5000, "Πώληση", 500, correctPersonId);
        Assertions.assertThrows(AssetSaveException.class, () -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestCorrectCity_1() {
        Asset asset = new Asset(1, "Αθήνα", 5000, "Πώληση", 500, correctPersonId);
        Assertions.assertDoesNotThrow(() -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestCorrectCity_2() {
        Asset asset = new Asset(1, "Θεσσαλονίκη", 5000, "Πώληση", 500, correctPersonId);
        Assertions.assertDoesNotThrow(() -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestCorrectCity_3() {
        Asset asset = new Asset(1, "Πάτρα", 5000, "Πώληση", 500, correctPersonId);
        Assertions.assertDoesNotThrow(() -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestCorrectCity_4() {
        Asset asset = new Asset(1, "Ηράκλειο", 5000, "Πώληση", 500, correctPersonId);
        Assertions.assertDoesNotThrow(() -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestCorrectAvailability_1() {
        Asset asset = new Asset(1, "Αθήνα", 5000, "Πώληση", 500, correctPersonId);
        Assertions.assertDoesNotThrow(() -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestCorrectAvailability_2() {
        Asset asset = new Asset(1, "Αθήνα", 5000, "Ενοικίαση", 500, correctPersonId);
        Assertions.assertDoesNotThrow(() -> {
            assetService.save(asset, correctPersonId);
        });
    }

    @Test
    public void SaveTestMissingValues() {
        Asset asset = new Asset(1, null, 5000001, "Πώληση", 500, correctPersonId);
        Assertions.assertThrows(AssetSaveException.class, () -> {
            assetService.save(asset, correctPersonId);
        });
    }

}