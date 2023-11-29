package com.iwa.test.chef;
/*
import com.iwa.test.recipe.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ChefServiceTest {

    @Autowired
    private ChefRepository chefRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ChefService chefService;

    @Test
    public void testCreateChef() {
        Chef chef = new Chef(1L, "Doe");
        Chef result = chefService.createChef(chef);
        assertEquals(chef.getId(), result.getId());
        assertEquals(chef.getName(), result.getName());
    }

    @Test
    public void getChefById() {
        recipeRepository.deleteAll();
        chefRepository.deleteAll();

        List<Chef> chefs = chefService.getChefs();
        assertTrue(chefs.isEmpty());

        Chef chef = new Chef(1L, "Doe");
        Optional<Chef> result = chefService.getChefById(chef.getId());
        assertTrue(result.isEmpty());

        chefService.createChef(chef);
        result = chefService.getChefById(chef.getId());
        assertNotNull(result);
        assertEquals(chef.getId(), result.get().getId());
        assertEquals(chef.getName(), result.get().getName());
    }


    @Test
    public void deleteChefById() {
        recipeRepository.deleteAll();
        chefRepository.deleteAll();

        Chef chef = new Chef(1L, "Doe");
        chefService.createChef(chef);
        Optional<Chef> result = chefService.getChefById(chef.getId());
        assertNotNull(result);

        boolean deleted = chefService.deleteChefById(chef.getId());
        assertTrue(deleted);

        result = chefService.getChefById(chef.getId());
        assertTrue(result.isEmpty());
    }

    @Test
    public void updateChefById() {
        recipeRepository.deleteAll();
        chefRepository.deleteAll();

        Chef chef = new Chef(1L, "Doe");
        chefService.createChef(chef);
        Optional<Chef> result = chefService.getChefById(chef.getId());
        assertNotNull(result);

        Chef updatedChef = new Chef(1L, "Doe Jr.");
        result = chefService.updateChefById(chef.getId(), updatedChef);
        assertNotNull(result);
        assertEquals(updatedChef.getId(), result.get().getId());
        assertEquals(updatedChef.getName(), result.get().getName());
    }
}
