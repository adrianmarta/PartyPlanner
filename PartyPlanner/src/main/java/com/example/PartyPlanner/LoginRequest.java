package com.example.PartyPlanner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class LoginRequest {
    private String email;
    private String password;
}
