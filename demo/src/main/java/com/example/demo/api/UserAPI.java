package com.example.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.IUserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserAPI {

	@Autowired
	private IUserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findOne(@PathVariable Long id){
		
		Optional<UserDTO> userOptional = userService.findById(id);
        return userOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
