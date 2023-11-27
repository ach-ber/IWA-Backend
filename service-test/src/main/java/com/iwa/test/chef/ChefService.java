package com.iwa.test.chef;
import com.iwa.test.recipe.Recipe;
import com.iwa.test.recipe.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefService {
    private ChefRepository chefRepository;
    private RecipeRepository recipeRepository;

    public ChefService(ChefRepository chefRepository, RecipeRepository recipeRepository) {
        this.chefRepository = chefRepository;
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    public Chef createChefWithRecipes(Chef chef, List<Recipe> recipes){

        recipes.stream().forEach(recipe -> recipe.setChef(chef));

        this.chefRepository.save(chef);
        this.recipeRepository.saveAll(recipes);

        return chef;
    }

    @Transactional
    public Chef createChef(Chef chef){
        return this.chefRepository.save(chef);
    }

    public List<Chef> getChefs(){
        System.out.println(this.chefRepository.findAll());
        return this.chefRepository.findAll();
    }

    public Optional<Chef> getChefById(Long id){
        return this.chefRepository.findById(id);
    }

    public Long getNumberOfChefs(){
        return this.chefRepository.count();
    }

    @Transactional
    public boolean deleteChefById(Long id){
        if (this.chefRepository.existsById(id)){
            this.chefRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Chef> updateChefById(Long id, Chef chef){
        Optional<Chef> existingChef = this.chefRepository.findById(id);

        if (existingChef != null){
            chef.setId(id);
            return Optional.of(this.chefRepository.save(chef));
        } else {
            return null;
        }
    }

    public void deleteAllChefs(){
        this.chefRepository.deleteAll();
    }
}
