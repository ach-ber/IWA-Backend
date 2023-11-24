package com.iwa.recruiterservice.init;
import com.iwa.recruiterservice.recruiter.RecruiterService;
import com.iwa.recruiterservice.recruiter.RecruiterUserRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class DataInitializer implements CommandLineRunner {
    private final RecruiterService recruiterService;
    public DataInitializer(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }
    @Override
    public void run(String... args) throws Exception {
        if (recruiterService.getNumberOfRecruiters() < 1) {
            List<RecruiterUserRequest> listRecruiter = getRecruiterUserList();
            for (RecruiterUserRequest recruiter : listRecruiter) {
                recruiterService.createRecruiter(recruiter);
            }
        } else {
            System.out.println("Recruiters already initialized");
        }
    }
    private static List<RecruiterUserRequest> getRecruiterUserList() {
        List<RecruiterUserRequest> listRecruiterUserRequests = new ArrayList<>();
        RecruiterUserRequest recruiterUser = new RecruiterUserRequest(
                "John", "Doe", "password", "0600000000", "JohnDoe@gmail.com",
                LocalDate.now(), "ROLE_USER",
                LocalDate.now(), null, 1, null);

        RecruiterUserRequest recruiterAdmin = new RecruiterUserRequest(
                "admin", "admin", "admin", "0600000000", "admin@gmail.com",
                LocalDate.now(), "ROLE_ADMIN",
                LocalDate.now(), null, 1, null);

        listRecruiterUserRequests.add(recruiterUser);
        listRecruiterUserRequests.add(recruiterAdmin);
        return listRecruiterUserRequests;
    }
}
