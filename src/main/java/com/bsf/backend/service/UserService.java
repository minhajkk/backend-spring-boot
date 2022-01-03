package com.bsf.backend.service;

import com.bsf.backend.config.MyUserDetails;
import com.bsf.backend.entity.User;
import com.bsf.backend.entity.UserRepository;
import com.bsf.backend.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return new MyUserDetails(user);
	}

	public User createUser(User user) throws UserExistException {
		User dbUser = userRepository.findByEmail(user.getEmail());

		if (dbUser != null) {
			throw new UserExistException(user.getEmail() + " Already exist");
		}
		return userRepository.save(user);
	}
}