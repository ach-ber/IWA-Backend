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
        if (recruiterService.getNumberOfRecruiters() < 1) {
            recruiterService.createRecruiter(new Recruiter("John", "Doe", "johndoe@gmail.com"));
        }
    }
}
