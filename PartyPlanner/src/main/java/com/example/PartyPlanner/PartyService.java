package com.example.PartyPlanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:3000")
public class PartyService {
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodieRepository goodieRepository;
    public List<Party> AllParties(){
        return partyRepository.findAll();
    }
    public Party findPartyById(String id) {
        return partyRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Party not found with ID: " + id));
    }

    public Party createParty(Party party) {
        try {
            if (party.getHostUser() != null) {
                // Fetch the host user based on the provided email
                Optional<User> optionalHost = userService.getUserByEmail(party.getHostUser().getEmail());
                if (optionalHost.isPresent()) {
                    party.setHostUser(optionalHost.get());
                    party.setJoined_participants(new ArrayList<>());

                    // If goodies are provided, save each one to the database
                    if (party.getGoodies() != null) {
                        for (Goodie goodie : party.getGoodies()) {
                            goodie.setUserEmail(party.getHostUser().getEmail()); // Set the user email for the goodie
                            goodieRepository.save(goodie); // Save each goodie to the database
                        }
                    }

                    // Save the party to the database and return it
                    return partyRepository.save(party);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The host doesn't exist!");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Host user must be set!");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Party addParticipant(String partyId, JoinRequest joinRequest) {
        Optional<Party> optionalParty = partyRepository.findById(partyId);
        if (optionalParty.isPresent()) {
            Party party = optionalParty.get();
            Optional<User> optionalUser = userService.getUserByEmail(joinRequest.getEmail());

            if (optionalUser.isPresent()) {
                User currentUser = optionalUser.get();

                // Check if the user is already a participant
                if (party.getJoined_participants().contains(currentUser)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are already a participant!");
                }

                // Save the goodie to the database
                if (joinRequest.getGoodie() != null) {
                    // Optional: Set the userEmail in the goodie
                    joinRequest.getGoodie().setUserEmail(currentUser.getEmail());
                    goodieRepository.save(joinRequest.getGoodie());
                }

                party.getGoodies().add(joinRequest.getGoodie());
                party.getJoined_participants().add(currentUser);
                return partyRepository.save(party);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Party not found!");
        }
    }
}
