package com.kdm.KodadProperties.service;


import com.amazonaws.services.applicationcostprofiler.model.S3BucketRegion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdm.KodadProperties.dao.DeletedPropertyDao;
import com.kdm.KodadProperties.dao.PropertyDao;
import com.kdm.KodadProperties.dao.PropertyOwnerDao;
import com.kdm.KodadProperties.dto.PropertyDto;
import com.kdm.KodadProperties.enums.PropertyCategory;
import com.kdm.KodadProperties.enums.PropertyStatus;
import com.kdm.KodadProperties.enums.PropertyType;
import com.kdm.KodadProperties.model.DeletedProperty;
import com.kdm.KodadProperties.model.Property;

import com.kdm.KodadProperties.model.PropertyOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;


import java.util.Arrays;
import java.util.List;

@Service
public class AdminService {

    @Value("${PATH_SEPARATOR}")
    private String PATH_SEPARATOR;

    private String allImgsPath = "";

    @Autowired
    private PropertyDao propertyDao;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ImagesUploaderService imagesUploaderService;
    @Autowired
    private DeletedPropertyDao deletedPropertyDao;
    @Autowired
    private PropertyOwnerDao propertyOwnerDao;

    public String uploadProperty(String propertyDtoString, MultipartFile defImg, List<MultipartFile> allImgs) throws JsonProcessingException {

        PropertyDto propertyDto = objectMapper.readValue(propertyDtoString, PropertyDto.class);

        Property property = Property.builder()
                .price(propertyDto.getPrice())
                .locality(propertyDto.getLocality())
                .town(propertyDto.getTown())
                .address(propertyDto.getAddress())
                .description(propertyDto.getDescription())
                .size(propertyDto.getSize())
                .propertyType(PropertyType.valueOf(propertyDto.getPropertyType()))
                .propertyCategory(PropertyCategory.valueOf(propertyDto.getPropertyCategory()))
                .propertyStatus(PropertyStatus.NOT_UPLOADED)
                .build();

        PropertyOwner propertyOwner=PropertyOwner.builder()
                .name(propertyDto.getPropertyOwner().getName())
                .mobileNumber(propertyDto.getPropertyOwner().getMobileNumber())
                .alternativeNumber(propertyDto.getPropertyOwner().getAlternativeNumber())
                .uploadAmountPaid(propertyDto.getPropertyOwner().getUploadAmountPaid())
                .build();
        try {
            property = propertyDao.uploadProperty(property);
            propertyOwner=propertyOwnerDao.savePropertyOwner(propertyOwner);
            imagesUploaderService.uploadDefaultImage(defImg, property.getPropertyId());
            imagesUploaderService.uploadAllImages(allImgs, property.getPropertyId());

            property.setDefImgPath(property.getPropertyId() + defImg.getOriginalFilename());
            property.setAllImgsPath(getAllImgsPathString(allImgs, allImgsPath, property.getPropertyId()));
            property.setPropertyOwner(propertyOwner);

            property = propertyDao.saveProperty(property);

            if (property.getId() != 0) {
                return "property uploaded successfully!!!";
            } else {
                return "unable to upload property!!!";
            }
        } catch (Exception e) {
            throw new IllegalStateException("unable to upload property!!! " + e.getMessage());
        }
    }

    private String getAllImgsPathString(List<MultipartFile> allImgs, String allImgsPath, String propertyId) {
        for (MultipartFile img : allImgs) {
            allImgsPath += propertyId + img.getOriginalFilename() + PATH_SEPARATOR;
        }
        return allImgsPath;
    }

    public List<Property> getAllProperties() {
        return propertyDao.getAllPropertiesWithoutPaging();
    }

    public PropertyDto getPropertyById(String propertyId) {
        Property property = propertyDao.getPropertyById(propertyId);
        try {
            if (property != null) {
                PropertyDto propertyDto = PropertyDto.builder()
                        .price(property.getPrice())
                        .locality(property.getLocality())
                        .size(property.getSize())
                        .description(property.getDescription())
                        .town(property.getTown())
                        .address(property.getAddress())
                        .propertyType(property.getPropertyType().toString())
                        .propertyCategory(property.getPropertyCategory().toString())
                        .build();
                return propertyDto;
            } else {
                throw new IllegalStateException("property with id doesn't exists!!!");
            }

        } catch (Exception e) {
            throw new IllegalStateException("unable to build propertyDto!!!" + e.getMessage());
        }
    }

    public String updatePropertyDetails(String propertyId, PropertyDto propertyDto) {
        try {
            Property property = propertyDao.getPropertyById(propertyId);

            if (propertyDto.getPrice() != 0) {
                property.setPrice(propertyDto.getPrice());
            }

            if (propertyDto.getTown() != null) {
                property.setTown(propertyDto.getTown());
            }

            if (propertyDto.getLocality() != null) {
                property.setLocality(propertyDto.getLocality());
            }

            property.setPropertyType(PropertyType.valueOf(propertyDto.getPropertyType()));
            property.setPropertyCategory(PropertyCategory.valueOf(propertyDto.getPropertyCategory()));
            property.setAddress(propertyDto.getAddress());
            property.setDescription(propertyDto.getDescription());
            property.setSize(propertyDto.getSize());

            propertyDao.saveProperty(property);

            return "property details updated successfully!!!";
        } catch (Exception e) {
            throw new IllegalStateException("unable to update property details!!!" + e.getMessage());
        }

    }

    public String deletePropertyById(String propertyId) {

        Property property=propertyDao.getPropertyById(propertyId);

        DeletedProperty deletedProperty = DeletedProperty.builder()
                .propertyId(property.getPropertyId())
                .price(property.getPrice())
                .locality(property.getLocality())
                .town(property.getTown())
                .propertyStatus(property.getPropertyStatus())
                .build();

        String[] temp=property.getAllImgsPath().split(PATH_SEPARATOR);
        List<String> allImgsPath= Arrays.asList(temp);
        try {
            imagesUploaderService.deleteDefaultImage(property.getDefImgPath());
            imagesUploaderService.deleteAllImages(allImgsPath);
            deletedPropertyDao.save(deletedProperty);
            propertyDao.deletePropertyById(property.getId());

            return "property with id " + propertyId + " deleted successfully!!!";
        } catch (Exception e) {
            throw new IllegalStateException("unable to delete the property with id "+propertyId+e.getMessage());
        }
    }
}
