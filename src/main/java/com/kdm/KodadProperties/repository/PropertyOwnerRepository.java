package com.kdm.KodadProperties.repository;

import com.kdm.KodadProperties.model.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyOwnerRepository extends JpaRepository<PropertyOwner,Long> {
}
