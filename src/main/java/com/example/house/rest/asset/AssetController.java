package com.example.house.rest.asset;

import com.example.house.entity.Asset;
import com.example.house.entity.Person;
import com.example.house.exception.AssetSaveException;
import com.example.house.exception.AssetSearchException;
import com.example.house.exception.PersonSearchException;
import com.example.house.exception.PersonValidationException;
import com.example.house.service.asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Asset asset) {
        try{
            assetService.save(asset);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        catch (AssetSaveException | PersonSearchException | AssetSearchException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getPersonAssets(@RequestHeader Map<String, String> header) {
        try{
            Person person = assetService.getPersonFromHeader(header);
            List<Asset> assetList = assetService.findAssetsByPersonId(person.getPersonId());
            return new ResponseEntity<>(assetList, HttpStatus.CREATED);
        }
        catch (PersonValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteUserByID(@RequestHeader Map<String, String> header, @PathVariable int id){
        Person person = assetService.getPersonFromHeader(header);
        return assetService.deleteByAssetId(person, id);
    }
}
