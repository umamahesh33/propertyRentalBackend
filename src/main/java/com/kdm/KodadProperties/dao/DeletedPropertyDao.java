package com.kdm.KodadProperties.dao;

import com.kdm.KodadProperties.model.DeletedProperty;
import com.kdm.KodadProperties.repository.DeletedPropertyRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class DeletedPropertyDao {

    @Autowired
    DeletedPropertyRepository deletedPropertyRepository;

    public DeletedProperty save(DeletedProperty deletedProperty){
        return deletedPropertyRepository.save(deletedProperty);
    }
}
