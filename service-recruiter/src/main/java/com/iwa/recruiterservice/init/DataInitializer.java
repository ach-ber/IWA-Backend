package com.iwa.recruiterservice.init;

import com.iwa.recruiterservice.recruiter.Recruiter;
import com.iwa.recruiterservice.recruiter.RecruiterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RecruiterService recruiterService;

    public DataInitializer(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (recruiterService.getNumberOfRecruiters() > 1) {
            System.out.println("Recruiters already inialized!");
            return;
        }

        Recruiter recruiter1 = new Recruiter("Paul", "Doe", "john.doe@gmail.com");
        Recruiter recruiter2 = new Recruiter("Jane", "Doe", "jane.doe@gmail.com");

        recruiterService.createRecruiter(recruiter1);
        recruiterService.createRecruiter(recruiter2);

    }
}
