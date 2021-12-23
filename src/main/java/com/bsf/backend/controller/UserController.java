package com.bsf.backend.controller;

import com.bsf.backend.entity.User;
import com.bsf.backend.entity.UserRepository;
import com.bsf.backend.exception.UserExistException;
import com.bsf.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> list(){
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> show(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity("", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Optional<User> update(@RequestBody User user, @PathVariable Long id) {

        Optional<User> dbUser = userRepository.findById(id);

        if(dbUser.isPresent()) {

            if (user.getFullName() != null) {
                dbUser.get().setFullName(user.getFullName());
            }
            if (user.getMobile() != null) {
                dbUser.get().setMobile(user.getMobile());
            }

            System.out.println("User.Id -> " + user.getId());

            userRepository.save(dbUser.get());
        }

        return dbUser;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) throws UserExistException {
        return userService.createUser(user);
    }

}
