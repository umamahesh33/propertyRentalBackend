package com.kdm.KodadProperties.service;

import com.kdm.KodadProperties.dao.PropertyDao;
import com.kdm.KodadProperties.dto.FullPropertyDetailsDto;
import com.kdm.KodadProperties.dto.MinPropertyDetailsDto;
import com.kdm.KodadProperties.enums.PropertyType;
import com.kdm.KodadProperties.model.Property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class UserServices {

    @Value("${PATH_SEPARATOR}")
    private String PATH_SEPARATOR;

    @Autowired
    PropertyDao propertyDao;

    @Autowired
    ImagesUploaderService imgService;

    public FullPropertyDetailsDto getPropertyById(String propertyId){
        try {
            Property property = propertyDao.getPropertyById(propertyId);

            String[] result=property.getAllImgsPath().split(PATH_SEPARATOR);
            List<String> allImgsPaths=new ArrayList<>(Arrays.asList(result));

            FullPropertyDetailsDto response= FullPropertyDetailsDto.builder()
                    .propertyId(property.getPropertyId())
                    .price(property.getPrice())
                    .locality(property.getLocality())
                    .address(property.getAddress())
                    .description(property.getDescription())
                    .size(property.getSize())
                    .town(property.getTown())
                    .propertyType(property.getPropertyType())
                    .allImgsPaths(allImgsPaths)
                    .propertyOwner(property.getPropertyOwner())
                    .build();

        return response;
        }catch (Exception e){
            throw new IllegalStateException("unable to load property: "+propertyId+ "/n"+ e.getMessage());
        }

    }

    public List<MinPropertyDetailsDto> getAllProperties(int pageNo) {

        List<MinPropertyDetailsDto> response=new ArrayList<>();

        try {
            List<Property> allProperties = propertyDao.getAllProperties(pageNo).getContent();

            for (Property property : allProperties) {
                MinPropertyDetailsDto minPropertyDetailsDto = MinPropertyDetailsDto.builder()
                        .price(property.getPrice())
                        .propertyId(property.getPropertyId())
                        .size(property.getSize())
                        .locality(property.getLocality())
                        .town(property.getTown())
                        .defImgPath(property.getDefImgPath())
                        .build();
                response.add(minPropertyDetailsDto);
            }
            return response;
        }catch (Exception e){
            throw new IllegalStateException("unable to process the request!!!"+e.getMessage());
        }
    }

    public List<MinPropertyDetailsDto> getByPropertyType(String propertyType,int pageNo) {

        List<MinPropertyDetailsDto> response=new ArrayList<>();
        try {
            List<Property> allLandPropertiesList = propertyDao.getByPropertyType(PropertyType.valueOf(propertyType.toUpperCase(Locale.ROOT)),
                                                                                                pageNo).getContent();

            for (Property property: allLandPropertiesList){
                MinPropertyDetailsDto minPropertyDetailsDto = MinPropertyDetailsDto.builder()
                        .price(property.getPrice())
                        .propertyId(property.getPropertyId())
                        .size(property.getSize())
                        .locality(property.getLocality())
                        .town(property.getTown())
                        .defImgPath(property.getDefImgPath())
                        .build();
                response.add(minPropertyDetailsDto);
            }
            return response;
        }catch (Exception e){
            throw new IllegalStateException("unable to process the request!!!"+e.getMessage());
        }
    }





























//    public byte[] downloadDefaultImage(String key) {
//        return imgService.downlaodDefaultImage(key);
//    }

//    public List<byte[]> getAllImages(String allImgs) {
//        String[] result=allImgs.split(PATH_SEPARATOR);
//        List<String> allImgsPaths=new ArrayList<>(Arrays.asList(result));
//        return imgService.getAllImages(allImgsPaths);
//    }

}
