package com.kdm.KodadProperties.service;

import com.kdm.KodadProperties.model.Admin;
import com.kdm.KodadProperties.repository.AdminRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AdminSecurityService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin=adminRepository.findByUsername(username);

        if(admin==null){
            throw new UsernameNotFoundException("admin with username was not registered!!!");
        }

        return admin;
    }
}
