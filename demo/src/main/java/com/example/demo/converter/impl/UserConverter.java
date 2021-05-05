package com.example.demo.converter.impl;

import org.springframework.stereotype.Component;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;

@Component
public class UserConverter extends CommonConverter<UserEntity, UserDTO> {

}
