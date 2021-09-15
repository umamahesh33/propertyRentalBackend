package com.kdm.KodadProperties.repository;

import com.kdm.KodadProperties.model.DeletedProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedPropertyRepository extends JpaRepository<DeletedProperty,Long> {
}
