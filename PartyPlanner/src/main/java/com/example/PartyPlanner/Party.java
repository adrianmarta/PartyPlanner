package com.example.PartyPlanner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document(collection= "party")
@AllArgsConstructor
@NoArgsConstructor
public class Party {
    @Id
    private String objectId;
    @DocumentReference
    private User hostUser;
    private String description;
    @DocumentReference
    private List<User> joined_participants;
    private float budget;
    @DocumentReference
    private List<Goodie> goodies;

}
