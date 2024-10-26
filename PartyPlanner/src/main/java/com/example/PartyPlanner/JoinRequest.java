package com.example.PartyPlanner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class JoinRequest {
    private String email; // Current user's email
    private Goodie goodie; // Goodie the user is bringing
}
