package com.bsf.backend.controller;

import com.bsf.backend.config.JwtTokenUtil;
import com.bsf.backend.entity.User;
import com.bsf.backend.entity.UserRepository;
import com.bsf.backend.exception.UserExistException;
import com.bsf.backend.security.JwtRequest;
import com.bsf.backend.security.JwtResponse;
import com.bsf.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtRequest.getUsername(), jwtRequest.getPassword()));

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) throws UserExistException {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));

        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile() {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();

        UserDetails details = (UserDetails) auth.getDetails();

        User user = userRepository.findByEmail(details.getUsername());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
