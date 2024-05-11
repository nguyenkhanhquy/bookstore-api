package com.bookstore.api.controller;

import com.bookstore.api.response.UserResponse;
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
public class UserController {

    private final UserService userService;

    // quick and dirty: inject users dao (use constructor injection)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // expose "/users" and return a list of users
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    // add mapping for GET "/users/{userId}"
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable int userId) {

        UserResponse response = userService.findById(userId);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // add mapping for POST "/users" - add new user
    @PostMapping("/users")
    public UserResponse addUser(@RequestBody User theUser) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theUser.setId(0);

        User dbUser = userService.save(theUser);

        UserResponse userResponse = new UserResponse();
        userResponse.setError(false);
        userResponse.setMessage("Added user id - " + dbUser.getId());
        userResponse.setUser(dbUser);

        return userResponse;
    }

    // add mapping for PUT "/users" - update existing user
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateCustomer(@PathVariable int userId,
                                                       @RequestBody User user) {

        UserResponse response = userService.findById(userId);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        User theUser = response.getUser();

        theUser.setUserName(user.getUserName());
        theUser.setFullName(user.getFullName());
        theUser.setEmail(user.getEmail());
        theUser.setGender(user.getGender());
        theUser.setImages(user.getImages());
        theUser.setPassword(user.getPassword());
        theUser.setRole(user.getRole());

        User dbUser = userService.save(theUser);

        response.setError(false);
        response.setMessage("Updated user id - " + dbUser.getId());
        response.setUser(dbUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // add mapping for DELETE "/users/{userId}" - delete user
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<UserResponse> deleteCustomer(@PathVariable int userId) {

        UserResponse response = userService.findById(userId);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        userService.deleteById(userId);

        response.setError(false);
        response.setMessage("deleted user id - " + userId);
        response.setUser(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/users/updateimages")
    public ResponseEntity<UserResponse> updateImages(@RequestParam("id") int userId,
                                                     @RequestParam("images") MultipartFile multipart) {

        UserResponse response = userService.findById(userId);
        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        User theUser = response.getUser();

        try {
            String fileName = multipart.getOriginalFilename();
            assert fileName != null;
            String newFileName = S3Util.urlFolderUser + userId + fileName.substring(fileName.lastIndexOf('.'));
            S3Util.uploadFile(newFileName, multipart.getInputStream());
            String urlImage = "https://" + S3Util.bucketName + ".s3.amazonaws.com/" + newFileName;

            theUser.setImages(urlImage);
            User dbUser = userService.save(theUser);

            response.setError(false);
            response.setMessage("Update images successfully");
            response.setUser(dbUser);
        } catch (IOException ex) {
            response.setError(true);
            response.setMessage("Error uploading file: " + ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
