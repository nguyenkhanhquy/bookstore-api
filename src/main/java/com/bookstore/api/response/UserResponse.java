package com.bookstore.api.response;

import com.bookstore.api.entity.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private boolean error;
    private String message;
    private User user;
}
