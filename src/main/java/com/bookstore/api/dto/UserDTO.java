package com.bookstore.api.dto;

import com.bookstore.api.entity.user.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private boolean error;
    private String message;
    private User user;
}
