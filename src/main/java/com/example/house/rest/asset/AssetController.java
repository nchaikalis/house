package com.example.house.rest.asset;

import com.example.house.entity.Asset;
import com.example.house.exception.AssetSaveException;
import com.example.house.exception.AssetSearchException;
import com.example.house.exception.PersonSearchException;
import com.example.house.service.asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        catch (AssetSaveException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (AssetSearchException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (PersonSearchException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
