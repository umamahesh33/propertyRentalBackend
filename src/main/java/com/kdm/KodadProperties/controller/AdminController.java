package com.kdm.KodadProperties.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdm.KodadProperties.dto.PropertyDto;
import com.kdm.KodadProperties.model.Property;
import com.kdm.KodadProperties.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/secured/api")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/get/properties")
    public ResponseEntity<List<Property>> getAllProperties(){
        return ResponseEntity.ok().body(adminService.getAllProperties());
    }

    @GetMapping("/update/property/{pid}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable("pid")String propertyId){
        return ResponseEntity.ok().body(adminService.getPropertyById(propertyId));
    }

    @PostMapping(value = "/upload/property",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String > uploadProperty(@RequestPart("propertyDtoString")String propertyDtoString,
                                                  @RequestPart("defImg")MultipartFile defImg,
                                                  @RequestPart("allImgs")List<MultipartFile> allImgs)throws JsonProcessingException {

        return ResponseEntity.ok(adminService.uploadProperty(propertyDtoString,defImg,allImgs));
    }

    @PutMapping("/submit/update/{pid}")
    public ResponseEntity<String> updatePropertyDetails(@PathVariable("pid")String propertyId,@RequestBody PropertyDto propertyDto){

        return ResponseEntity.ok().body(adminService.updatePropertyDetails(propertyId,propertyDto));

    }

    @DeleteMapping("/delete/property/{pid}")
    public ResponseEntity<String > deleteProperty(@PathVariable("pid")String propertyId){
        return ResponseEntity.ok().body(adminService.deletePropertyById(propertyId));
    }
}
