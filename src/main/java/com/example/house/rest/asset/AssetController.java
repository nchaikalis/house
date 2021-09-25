package com.example.house.rest.asset;

import com.example.house.entity.Asset;
import com.example.house.exception.AssetSaveException;
import com.example.house.exception.AssetSearchException;
import com.example.house.exception.PersonSearchException;
import com.example.house.exception.PersonValidationException;
import com.example.house.security.JwtTokenProvider;
import com.example.house.service.asset.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            String username = assetService.getToken(header);
            return new ResponseEntity<>(username, HttpStatus.CREATED);
        }
        catch (PersonValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
