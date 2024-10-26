package com.example.PartyPlanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/parties")
public class PartyController {
    @Autowired
    private PartyService partyService;
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<Party>> getAllParties() {
        List<Party> parties = partyService.AllParties();
        for (Party party : parties) {
            party.setObjectId(party.getObjectId());
        }
        return new ResponseEntity<>(parties, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Party> createParty(@RequestBody Party party) {
        return new ResponseEntity<>(partyService.createParty(party), HttpStatus.CREATED);
    }
    @PostMapping("/join")
    public ResponseEntity<Party> joinParty(
            @RequestParam String partyId,
            @RequestParam String email,
            @RequestBody Goodie goodie) {

        JoinRequest joinRequest = new JoinRequest(email,goodie); // Create JoinRequest object

        Party updatedParty = partyService.addParticipant(partyId, joinRequest);
        return ResponseEntity.ok(updatedParty);
    }
    @GetMapping("/{partyId}")
    public ResponseEntity<Party> getPartyById(@PathVariable String partyId) {
        Party party = partyService.findPartyById(partyId);
        return new ResponseEntity<>(party, HttpStatus.OK);
    }
}
