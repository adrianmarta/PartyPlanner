package com.example.PartyPlanner;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection= "goodie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Goodie {
    @Id
    private String Id;
    private String UserEmail;
    private String name;
    private int price;

}
