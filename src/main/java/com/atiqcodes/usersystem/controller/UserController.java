/* Developed by Atiqur Rahman */
package com.atiqcodes.usersystem.controller;

import com.atiqcodes.usersystem.exception.UserNotFoundException;
import com.atiqcodes.usersystem.model.User;
import com.atiqcodes.usersystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping()
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("user/add")
    public User add(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("user/getAll")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id){
//        return userRepository.findById(id)
//                .orElseThrow(() -> new  UserNotFoundException(id));
//    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    @PutMapping("user/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

//    @DeleteMapping("user/{id}")
//    public String deleteUser(@PathVariable Long id){
//
//        if(!userRepository.existsById(id)){
//            throw new UserNotFoundException(id);
//        }
//        userRepository.deleteById(id);
//        return "User with id "+id+" has been deleted successfully.";
//    }

    @DeleteMapping("user/{ids}")
    public String deleteUserIds(@PathVariable("ids") List<Long> ids){

        ids.forEach(d->{
            if(userRepository.existsById(d)){
                userRepository.deleteById(d);
            }else {
                throw new UserNotFoundException(d);
            }
        });
        return "User with ids have been deleted successfully.";
    }
}
