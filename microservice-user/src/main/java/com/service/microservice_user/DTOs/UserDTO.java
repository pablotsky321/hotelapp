package com.service.microservice_user.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private long id;
    private String email;
    private String firstName;
    private String lastName;

}
