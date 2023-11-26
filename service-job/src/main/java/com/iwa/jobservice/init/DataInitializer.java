package com.iwa.jobservice.init;

import com.iwa.jobservice.job.Job;
import com.iwa.jobservice.job.JobService;
import com.iwa.jobservice.jobcategory.Jobcategory;
import com.iwa.jobservice.jobcategory.JobcategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JobcategoryService jobcategoryService;
    private final JobService jobService;

    public DataInitializer(JobcategoryService jobcategoryService, JobService jobService) {
        this.jobcategoryService = jobcategoryService;
        this.jobService = jobService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (jobService.getNumberOfJobs() < 2) {
            List<Job> jobs = new ArrayList<>();
            jobs.add(new Job("barman", 1690332272l, 1701670786l, "Free lunch every day", 1800f, null, null));
            jobs.add(new Job("Web Developer Engineer", 1674688002000l, 1677341566000l, "Flexible hours, health insurance", 80000.0f, 13l, 1l));

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
            for (String category : categoriesList) {
                jobcategoryService.createJobcategory(new Jobcategory(category));
            }
            jobService.createJob(jobs);
        } else {
            System.out.println("Jobs already initialized");
        }
    }
}
