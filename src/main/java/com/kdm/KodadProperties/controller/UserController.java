package com.kdm.KodadProperties.controller;


import com.kdm.KodadProperties.dto.FullPropertyDetailsDto;
import com.kdm.KodadProperties.dto.MinPropertyDetailsDto;
import com.kdm.KodadProperties.service.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user/get")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping("/properties/{pageNo}")
    public ResponseEntity<List<MinPropertyDetailsDto>> getAllProperties(@PathVariable("pageNo")int pageNo){
        return ResponseEntity.ok().body(userServices.getAllProperties(pageNo));
    }

    @GetMapping("/properties/{propertyType}/{pageNo}")
    public ResponseEntity<List<MinPropertyDetailsDto>> getByPropertyType(@PathVariable("propertyType")String propertyType,
                                                                            @PathVariable("pageNo")int pageNo){
        return ResponseEntity.ok().body(userServices.getByPropertyType(propertyType,pageNo));

    }

    @GetMapping("/property/{pid}")
    public ResponseEntity<FullPropertyDetailsDto> getPropertyWithId(@PathVariable("pid")String pid){
        return ResponseEntity.ok(userServices.getPropertyById(pid));
    }

//    NOT AN EFFICIENT METHOD , JUST RETURN LIST OF THE PATHS THATHS ENOUGH CALL IMAGES FROM FRONT END
//    @GetMapping(value = "/allimages",produces = MediaType.IMAGE_JPEG_VALUE)
//    public List<byte[]> getAllImages(@RequestParam("allImgs")String allImgs){
//        return userServices.getAllImages(allImgs);
//    }


//    @GetMapping(value = "/download/{key}",produces = MediaType.IMAGE_JPEG_VALUE)
//    public byte[] downloadDefaultImage(@PathVariable("key")String key){
//        return userServices.downloadDefaultImage(key);
//    }



}
