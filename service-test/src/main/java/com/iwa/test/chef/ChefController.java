package com.iwa.test.chef;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chefs")

public class ChefController {

    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @PostMapping
    public ResponseEntity<Chef> createChef(@RequestBody Chef newChef){
        Chef result = chefService.createChef(newChef);

        if (result != null){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Chef> getAllChefs(){
        return chefService.getChefs();
    }

    @GetMapping("/count")
    public Long getNumberOfChefs() {
        return chefService.getNumberOfChefs();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Chef> getChefById(@PathVariable Long id){
        return chefService.getChefById(id)
                .map(chef -> ResponseEntity.ok().body(chef))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chef> updateChefById(@PathVariable Long id, @RequestBody Chef updatedChef){
        return chefService.updateChefById(id, updatedChef)
                .map(chef -> new ResponseEntity(chef, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChefById(@PathVariable Long id){
        boolean deleted = chefService.deleteChefById(id);

        if (deleted){
            return new ResponseEntity<>("Chef deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Chef not found", HttpStatus.NOT_FOUND);
        }
    }

}