package com.jwtAuth.security.model.response;

import com.jwtAuth.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
