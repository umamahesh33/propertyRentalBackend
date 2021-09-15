package com.kdm.KodadProperties.dao;

import com.kdm.KodadProperties.dto.MinPropertyDetailsDto;
import com.kdm.KodadProperties.enums.PropertyCategory;
import com.kdm.KodadProperties.enums.PropertyStatus;
import com.kdm.KodadProperties.enums.PropertyType;
import com.kdm.KodadProperties.model.Property;
import com.kdm.KodadProperties.repository.PropertyRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PropertyDao {

    @Value("${PAGE_SIZE}")
    private int PAGE_SIZE;

    @Autowired
    PropertyRepository propertyRepository;


    public Property uploadProperty(Property property){
        try{
            property=propertyRepository.save(property);
            property.setPropertyId("P:"+property.getId());
            property.setPropertyStatus(PropertyStatus.NOT_SOLD);
            return propertyRepository.save(property);
        }catch (Exception e){
            throw new IllegalStateException("unable to upload property!!! " + e.getMessage());
        }
    }

    public Property saveProperty(Property property){
        return propertyRepository.save(property);
    }

    public Property getPropertyById(String propertyId){
        try{
            return propertyRepository.findByPropertyId(propertyId);
        }catch (Exception e){
            throw new IllegalStateException("property could not be found!!! ");
        }
    }

    public Page<Property> getAllProperties(int pageNo) {
        Pageable pageable= PageRequest.of(pageNo-1,PAGE_SIZE,Sort.by("PropertyCategory").descending());
        Page<Property> page=propertyRepository.findAll(pageable);
        return page;
    }

    public Page<Property> getByPropertyType(PropertyType propertyType,int pageNo){
        Pageable pageable=PageRequest.of(pageNo-1,PAGE_SIZE);
        Page<Property> page=  propertyRepository.getByPropertyType(propertyType.toString(),pageable);
        return page;
    }

    public List<Property> getAllPropertiesWithoutPaging(){
       return propertyRepository.findAll();
    }


    @Transactional
    public void deletePropertyById(long Id){
        try{
            propertyRepository.deleteById(Id);
        }catch (Exception e){
            throw new IllegalStateException("unable to delete the property!!!"+e.getMessage());
        }
    }
}
