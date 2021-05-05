package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converter.impl.UserConverter;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserConverter userConverter;
	
	@Override
	public List<UserDTO> findAll() {
		List<UserEntity> userEntities = userRepo.findAll();
		
		List<UserDTO> userDTOs = userEntities.stream().map(item -> userConverter.convertToDTO(item)).collect(Collectors.toList());
		
		return userDTOs;
	}

	@Override
	public Optional<UserDTO> findById(Long userId) {
		Optional<UserEntity> userEntity = userRepo.findById(userId);
		
		UserDTO userDTO = userConverter.convertToDTO(userEntity.get());
		
		return Optional.of(userDTO);
	}

}
