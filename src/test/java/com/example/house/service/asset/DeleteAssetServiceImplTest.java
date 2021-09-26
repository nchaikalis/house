package com.example.house.service.asset;

import com.example.house.entity.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DeleteAssetServiceImplTest {
    private final int person_id = 44; // set a correct ID from the DB in order for all tests to work
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

}