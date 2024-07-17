package com.service.microservice_user.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {

    private String numDoc;
    private String email;
    private String firstName;
    private String lastName;
    private long typeDoc;

}
