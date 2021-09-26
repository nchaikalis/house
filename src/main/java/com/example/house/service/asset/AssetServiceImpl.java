package com.example.house.service.asset;

import com.example.house.dto.asset.PersonAssetDto;
import com.example.house.dto.mapper.AssetMapper;
import com.example.house.entity.Asset;
import com.example.house.entity.Person;
import com.example.house.exception.AssetSaveException;
import com.example.house.exception.AssetSearchException;
import com.example.house.exception.DeleteAssetException;
import com.example.house.exception.PersonSearchException;
import com.example.house.repository.AssetRepository;
import com.example.house.repository.PersonRepository;
import com.example.house.security.FindUsernameFromHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class AssetServiceImpl implements AssetService{

    private final int assetSquareMeterMin = 20;
    private final int assetSquareMeterMax = 1000;
    private final int assetPriceMin = 50;
    private final int assetPriceMax = 5000000;
    private final ArrayList<String> acceptedCitiesList = new ArrayList<>(
            Arrays.asList("Αθήνα", "Θεσσαλονίκη", "Πάτρα", "Ηράκλειο"));
    private final ArrayList<String> acceptedAvailabilitiesList = new ArrayList<>(
            Arrays.asList("Ενοικίαση", "Πώληση"));

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonAssetDto> findAssetsByPersonId(int person_id) {
        List<Asset> assetList = assetRepository.findAssetsByPersonId(person_id);
        return TransformAssetListToDtoList(assetList);
    }

    @Override
    public void save(Asset asset) {
        validateUser(asset);
        validateAnAsset(asset);
        try {
            assetRepository.save(asset);
        } catch (AssetSaveException e) {
            throw new AssetSaveException("Asset can not be saved" + e);
        }
    }

    @Override
    public Person getPersonFromHeader(Map<String, String> header) {
        FindUsernameFromHeader findUsernameFromHeader = new FindUsernameFromHeader(header);
        String username = findUsernameFromHeader.retrieveUsername();
        return findPersonFromUsername(username);
    }

    @Override
    public boolean deleteByAssetId(Person person, int asset_id) {
        Optional<List<PersonAssetDto>> personAssetList = Optional.ofNullable(findAssetsByPersonId(person.getPersonId()));
        if(personAssetList.isEmpty()) {
            throw new AssetSearchException("The user " + person.getUsername() + " has no assets");
        }
        checkIfAssetBelongsToPerson(personAssetList, asset_id);
        try{
            assetRepository.deleteByAssetId(asset_id);
            return true;
        } catch (Exception e) {
            throw new DeleteAssetException("Something went wrong the asset can not be deleted.\nError:\n" + e);
        }

    }

    private void validateUser(Asset asset) {
        if(asset.getPersonId() == null) {
            throw new PersonSearchException("The person ID required.");
        }

        Person person = personRepository.findByPersonId(asset.getPersonId().getPersonId());
        if(person == null) {
            throw new PersonSearchException("The person ID is not exists.");
        }
        asset.setPersonId(person);
    }

    private void validateAnAsset(Asset asset) {
        if(asset == null) {
            throw new AssetSaveException("Asset can not be empty");
        }

        validateAssetArea(asset);
        validateAssetAvailability(asset);
        validateAssetSquareMeter(asset);
        validateAssetPrice(asset);
    }

    private void validateAssetArea(Asset asset) {
        if(asset.getArea() == null || asset.getArea().isEmpty()) {
            throw new AssetSaveException("Area required");
        }
        else if(!acceptedCitiesList.contains(asset.getArea())){
            throw new AssetSaveException("Wrong area input. Accepted values: " + acceptedCitiesList);
        }
    }

    private void validateAssetAvailability(Asset asset) {
        if (asset.getAvailability() == null || asset.getAvailability().isEmpty()) {
            throw new AssetSaveException("Availability required");
        }
        else if(!acceptedAvailabilitiesList.contains(asset.getAvailability())) {
            throw new AssetSaveException("Wrong availability input. Accepted values: " + acceptedAvailabilitiesList);
        }
    }

    private void validateAssetSquareMeter(Asset asset) {
        if(asset.getSquareMeter() == 0) {
            throw new AssetSaveException("Square meters required.");
        }
        if(asset.getSquareMeter() < assetSquareMeterMin || asset.getSquareMeter() > assetSquareMeterMax) {
            throw new AssetSaveException("Wrong square meter. Accepted values are from: " + assetSquareMeterMin + " to: " + assetSquareMeterMax);
        }
    }

    private void validateAssetPrice(Asset asset) {
        if(asset.getPrice() == 0) {
            throw new AssetSaveException("Price required.");
        }
        else if(asset.getPrice() < assetPriceMin || asset.getPrice() > assetPriceMax) {
            throw new AssetSaveException("Wrong price. Accepted values are from: " + assetPriceMin + " to: " + assetPriceMax);
        }
    }

    private Person findPersonFromUsername(String username) {
        Person person = personRepository.findByUsername(username);
        if(person == null) {
            throw new PersonSearchException("The person ID is not exists.");
        }
        return person;
    }

    private void checkIfAssetBelongsToPerson(Optional<List<PersonAssetDto>> personAssets, int asset_id) {
        // we have to also cast the int to String in order to manage to return a boolean.
        boolean itBelongs = personAssets.map(list -> list.stream().anyMatch(asset -> String.valueOf(asset.getAssetId()).equals(String.valueOf(asset_id)))).orElse(Boolean.FALSE);
        if(!itBelongs){
            throw new AssetSearchException("No matching assets belong to the current user.");
        }
    }

    private List<PersonAssetDto> TransformAssetListToDtoList(List<Asset> assetList) {
        List<PersonAssetDto> personAssetDtoList = AssetMapper.INSTANCE.getPersonAssetListDto(assetList);
        if(personAssetDtoList == null || personAssetDtoList.isEmpty()) {
            throw new AssetSearchException("No matching assets belong to the current user.");
        }
        return personAssetDtoList;
    }
}
