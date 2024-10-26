package com.example.PartyPlanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodieService {
    private final GoodieRepository goodieRepository;

    @Autowired
    public GoodieService(GoodieRepository goodieRepository) {
        this.goodieRepository = goodieRepository;
    }

    // Create a new goodie
    public Goodie createGoodie(Goodie goodie) {
        return goodieRepository.save(goodie);
    }

    // Get all goodies
    public List<Goodie> getAllGoodies() {
        return goodieRepository.findAll();
    }

    // Get a goodie by ID
    public Goodie getGoodieById(String id) {
        return goodieRepository.findById(id).orElse(null); // Or throw an exception
    }

    // Delete a goodie by ID
    public void deleteGoodie(String id) {
        goodieRepository.deleteById(id);
    }
}
