package com.iwa.jobservice.init;

import com.iwa.jobservice.jobcategory.Jobcategory;
import com.iwa.jobservice.jobcategory.JobcategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JobcategoryService jobcategoryService;

    public DataInitializer(JobcategoryService jobcategoryService) {
        this.jobcategoryService = jobcategoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (jobcategoryService.getNumberOfJobcategories() > 1) {
            System.out.println("Jobcategories already inialized!");
            return;
        }

        String[] categoriesList = {
                "Agriculture, Viticulture, Pêche",
                "Hôtellerie de plein air, Club vacances, Camping, Animation",
                "Hôtels, cafés, bars, restaurants, fast-foods",
                "Evenementiel",
                "Casinos, Parcs d'attraction",
                "Administration, Espaces culturels, Tourisme",
                "Montagne, Ski",
                "Mer, Plongée, Sports/Loisirs Nautiques",
                "Sécurité, Gardiennage",
                "Logistique, Transport",
                "Baby sitting, Services à la personne",
                "Commerce, Achats, Vente",
                "SPA, Esthétique, Coiffure",
                "Autre"
        };

        List<Jobcategory> jobcategories = new ArrayList<>(categoriesList.length);
        for (String category : categoriesList) {
            jobcategories.add(new Jobcategory(category));
        }

        jobcategoryService.createJobcategory(jobcategories);
    }
}
