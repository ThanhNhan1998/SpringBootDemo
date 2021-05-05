package com.example.demo.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MyUser;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userCustomService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserName(username);
        
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity item: userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+item.getCode()));
        }
        
        MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(), true, true, true, true, authorities);
        
        BeanUtils.copyProperties(userEntity, myUser);
        
        return myUser;
    }
}