package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.UserDTO;

public interface IUserService {

	List<UserDTO> findAll();
	
	Optional<UserDTO> findById(Long userId);
}
