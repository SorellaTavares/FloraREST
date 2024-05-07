package com.example.florarest.service;


import com.example.florarest.data.FloraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FloraService {
    private final FloraRepository floraRepository;

    @Autowired
    public FloraService(FloraRepository floraRepository) {
        this.floraRepository = floraRepository;
    }

    @Transactional
    public List<Flora> getAllFlora() {
        return floraRepository.findAll();
    }

    @Transactional
    public List<String> getAllCommonNames() {
        List<Flora> allFloraObjects = floraRepository.findAll();
        List<String> allCommonNames = new ArrayList<>();
        for (Flora flora : allFloraObjects) {
            allCommonNames.add(flora.getName());
        }
        return allCommonNames;
    }

    @Transactional
    public List<String> getAllSciNames() {
        List<Flora> allFloraObjects = floraRepository.findAll();
        List<String> allSciNames = new ArrayList<>();
        for (Flora flora : allFloraObjects) {
            allSciNames.add(flora.getSciName());
        }
        return allSciNames;
    }

    @Transactional
    public void createFlora(Flora flora) {
        floraRepository.save(flora);
    }

    public Flora getFloraByName(String name) {
        Optional<Flora> optionalFlora = floraRepository.findByName(name);
        return optionalFlora.orElse(null);
    }

    @Transactional
    public void updateFlora(String name, String sciName) {
        Flora flora = floraRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("Flora not found in database."));
        flora.setSciName(sciName);
        floraRepository.save(flora);
    }

    @Transactional
    public String deleteFlora(String name) {
        floraRepository.deleteByName(name);
        return "Flora removed from database";
    }

}
