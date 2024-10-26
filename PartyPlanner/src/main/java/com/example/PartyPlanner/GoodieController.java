package com.example.PartyPlanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goodies")
@CrossOrigin(origins = "http://localhost:3000") // Allow CORS for the specified origin
public class GoodieController {
    private final GoodieService goodieService;

    @Autowired
    public GoodieController(GoodieService goodieService) {
        this.goodieService = goodieService;
    }

    @PostMapping
    public ResponseEntity<Goodie> createGoodie(@RequestBody Goodie goodie) {
        Goodie createdGoodie = goodieService.createGoodie(goodie);
        return new ResponseEntity<>(createdGoodie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Goodie>> getAllGoodies() {
        List<Goodie> goodies = goodieService.getAllGoodies();
        return new ResponseEntity<>(goodies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Goodie> getGoodieById(@PathVariable String id) {
        Goodie goodie = goodieService.getGoodieById(id);
        if (goodie != null) {
            return new ResponseEntity<>(goodie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoodie(@PathVariable String id) {
        goodieService.deleteGoodie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
