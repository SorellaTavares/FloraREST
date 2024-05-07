package com.example.florarest.REST;


import com.example.florarest.service.Flora;
import com.example.florarest.service.FloraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class FloraRESTController {

    private final FloraService floraService;

    @Autowired
    public FloraRESTController(FloraService floraService) {
        this.floraService = floraService;
    }

    @GetMapping("/rs/flora")
    public List<Flora> getAllFlora() {
        System.out.println("printing entire Flora database.");
        return floraService.getAllFlora();
    }

    @GetMapping("/rs/floracommonnames")
    public List<String> getAllCommonNames() {
        System.out.println("printing all common names.");
        return floraService.getAllCommonNames();
    }

    @GetMapping("/rs/florascientificnames")
    public List<String> getAllSciNames() {
        System.out.println("printing all scientific names.");
        return floraService.getAllSciNames();
    }

    @PostMapping("/rs/florapost/{name}/{sciname}")
    public String postFlora(@PathVariable String name, @PathVariable String sciname) {
        Flora flora = new Flora(name, sciname);
        floraService.createFlora(flora);
        System.out.println("adding " + flora.getName() + " in Flora database.");
        return "Flora added to database.";
    }

    @PutMapping("/rs/floraput/{name}/{sciname}")
    public String putFlora(@PathVariable String name, @PathVariable String sciname, @RequestBody Flora updatedFlora) {
        Flora existingFlora = floraService.getFloraByName(name);
        if (existingFlora == null) {
            return "Not found";
        }
        existingFlora.setName(updatedFlora.getName());
        existingFlora.setSciName(updatedFlora.getSciName());
        floraService.updateFlora(existingFlora.getName(), existingFlora.getSciName());
        System.out.println(existingFlora.getName() + " has been adjusted.");
        return existingFlora.getName() + " " + existingFlora.getSciName() + " has been adjusted.";
    }

    @DeleteMapping("/rs/floradelete/{name}")
    public String deleteFlora(@PathVariable String name) {
        floraService.deleteFlora(name);
        System.out.println("Removed from Flora database.");
        return "Removed from Flora database.";
    }

}
