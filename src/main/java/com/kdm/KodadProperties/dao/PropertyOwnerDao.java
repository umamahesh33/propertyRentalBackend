package com.kdm.KodadProperties.dao;

import com.kdm.KodadProperties.model.PropertyOwner;
import com.kdm.KodadProperties.repository.PropertyOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PropertyOwnerDao {

    @Autowired
    PropertyOwnerRepository propertyOwnerRepository;

    public PropertyOwner savePropertyOwner(PropertyOwner propertyOwner){
        try{
            return propertyOwnerRepository.save(propertyOwner);
        }catch (Exception e){
            throw new IllegalStateException("unexpected error occurred, unable to upload owner info!!!");
        }
    }

}
