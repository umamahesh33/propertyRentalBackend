package com.kdm.KodadProperties.repository;

import com.kdm.KodadProperties.enums.PropertyType;
import com.kdm.KodadProperties.model.Property;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {

    Property findByPropertyId(String propertyId);

    @Query(value = "SELECT * FROM property WHERE property_type=?1 ORDER BY property_category DESC",nativeQuery = true)
    Page<Property> getByPropertyType(String propertyType,Pageable pageable);

//    void deleteByPropertyId(String propertyId);
}
