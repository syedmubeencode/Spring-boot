package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Flux<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Mono<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public Mono<User> createUser(User user) {
		return userRepository.save(user);
	}

	public Mono<User> updateUser(Long id, User updatedUser) {
		return userRepository.findById(id).flatMap(existingUser -> {
			existingUser.setName(updatedUser.getName());
			existingUser.setEmail(updatedUser.getEmail());
			return userRepository.save(existingUser);
		});
	}

	public Mono<Void> deleteUser(Long id) {
		return userRepository.deleteById(id);
	}
}
