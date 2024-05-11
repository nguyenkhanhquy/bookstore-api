package com.bookstore.api.controller;

import com.bookstore.api.dto.UserDTO;
import com.bookstore.api.entity.user.User;
import com.bookstore.api.service.UserService;
import com.bookstore.api.util.S3Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    private final UserService userService;

    // quick and dirty: inject users dao (use constructor injection)
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // expose "/users" and return a list of users
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    // add mapping for GET "/users/{userId}"
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> findById(@PathVariable int userId) {

        User theUser = userService.findById(userId);

        UserDTO userDTO = new UserDTO();

        // throw exception if null
        if (theUser == null) {
            userDTO.setError(true);
            userDTO.setMessage("user id not found - " + userId);
            return new ResponseEntity<>(userDTO, HttpStatus.NOT_FOUND);
        }

        userDTO.setError(false);
        userDTO.setMessage("success");
        userDTO.setUser(theUser);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // add mapping for POST "/users" - add new user
    @PostMapping("/users")
    public UserDTO addCustomer(@RequestBody User theUser) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theUser.setId(0);

        User dbUser = userService.save(theUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setError(false);
        userDTO.setMessage("added user id - " + dbUser.getId());
        userDTO.setUser(dbUser);

        return userDTO;
    }

    // add mapping for PUT "/users" - update existing user
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDTO> updateCustomer(@PathVariable int userId,
                                                  @RequestBody User user) {

        User theUser = userService.findById(userId);

        UserDTO userDTO = new UserDTO();

        // throw exception if null
        if (theUser == null) {
            userDTO.setError(true);
            userDTO.setMessage("user id not found - " + userId);
            return new ResponseEntity<>(userDTO, HttpStatus.NOT_FOUND);
        }

        theUser.setUserName(user.getUserName());
        theUser.setFullName(user.getFullName());
        theUser.setEmail(user.getEmail());
        theUser.setGender(user.getGender());
        theUser.setImages(user.getImages());
        theUser.setPassword(user.getPassword());
        theUser.setRole(user.getRole());

        User dbUser = userService.save(theUser);

        userDTO.setError(false);
        userDTO.setMessage("success");
        userDTO.setUser(dbUser);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // add mapping for DELETE "/users/{userId}" - delete user
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<UserDTO> deleteCustomer(@PathVariable int userId) {

        User theUser = userService.findById(userId);

        UserDTO userDTO = new UserDTO();

        // throw exception if null
        if (theUser == null) {
            userDTO.setError(true);
            userDTO.setMessage("user id not found - " + userId);
            return new ResponseEntity<>(userDTO, HttpStatus.NOT_FOUND);
        }

        userService.deleteById(userId);

        userDTO.setError(false);
        userDTO.setMessage("deleted user id - " + userId);
        userDTO.setUser(theUser);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/users/updateimages")
    public ResponseEntity<UserDTO> updateImages(@RequestParam("id") int userId,
                                                @RequestParam("images") MultipartFile multipart) {

        User theUser = userService.findById(userId);

        UserDTO userDTO = new UserDTO();

        // throw exception if null
        if (theUser == null) {
            userDTO.setError(true);
            userDTO.setMessage("user id not found - " + userId);
            return new ResponseEntity<>(userDTO, HttpStatus.NOT_FOUND);
        }

        try {
            String fileName = multipart.getOriginalFilename();
            String newFileName = S3Util.urlFolderUser + userId + fileName.substring(fileName.lastIndexOf('.'));
            S3Util.uploadFile(newFileName, multipart.getInputStream());
            String urlImage = "https://" + S3Util.bucketName + ".s3.amazonaws.com/" + newFileName;
            theUser.setImages(urlImage);
            User dbUser = userService.save(theUser);
            userDTO.setError(false);
            userDTO.setMessage("Update images successfully");
            userDTO.setUser(dbUser);
        } catch (IOException ex) {
            userDTO.setError(true);
            userDTO.setMessage("Error uploading file: " + ex.getMessage());
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
