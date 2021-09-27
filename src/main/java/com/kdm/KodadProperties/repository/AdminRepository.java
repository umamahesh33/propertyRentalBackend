package com.kdm.KodadProperties.repository;

import com.kdm.KodadProperties.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findByUsername(String username);

}
