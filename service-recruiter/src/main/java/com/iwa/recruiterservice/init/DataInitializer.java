package com.iwa.recruiterservice.init;
import com.iwa.recruiterservice.address.Address;
import com.iwa.recruiterservice.address.AddressService;
import com.iwa.recruiterservice.establishment.Establishment;
import com.iwa.recruiterservice.establishment.EstablishmentService;

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

    private final AddressService addressService;

    private final EstablishmentService establishmentService;
    public DataInitializer(RecruiterService recruiterService, AddressService addressService, EstablishmentService establishmentService) {
        this.recruiterService = recruiterService;
        this.addressService = addressService;
        this.establishmentService = establishmentService;
    }
    @Override
    public void run(String ...args) throws Exception {
        if (recruiterService.getNumberOfRecruiters() < 1) {
            List<RecruiterUserRequest> listRecruiter = getRecruiterUserList();
            for (RecruiterUserRequest recruiter : listRecruiter) {
                recruiterService.addRecruiterUser(recruiter);
            }
        } else {
            System.out.println("Recruiters already initialized");
        }

        if (addressService.getNumberOfAddresses() < 1) {
            addressService.createAddress(new Address("123", "Main Street", "Apt 456", "Gilles-la-Forêt", "39119", "Îles Mineures Éloignées des États-Unis"));
        } else {
            System.out.println("Addresses already initialized");
        }

        if (establishmentService.getNumberOfEstablishments() < 1) {
            establishmentService.createEstablishment(new Establishment("mcdo", 1L, 1L));
        } else {
            System.out.println("Establishments already initialized");
        }
    }
    private static List<RecruiterUserRequest> getRecruiterUserList() {
        List<RecruiterUserRequest> listRecruiterUserRequests = new ArrayList<>();
        RecruiterUserRequest recruiterFree = new RecruiterUserRequest(
                "John", "Doe", "password", "0600000000", "JohnDoe@gmail.com",
                LocalDate.now(), "ROLE_FREE",
                LocalDate.now(), null, 1, null);

        RecruiterUserRequest recruiterGold = new RecruiterUserRequest(
                "Peter", "Martin", "password", "0600000000", "PeterMartin@gmail.com",
                LocalDate.now(), "ROLE_GOLD",
                LocalDate.now(), null, 1, null);

        RecruiterUserRequest recruiterAdmin = new RecruiterUserRequest(
                "admin", "admin", "admin", "0600000000", "admin@gmail.com",
                LocalDate.now(), "ROLE_ADMIN",
                LocalDate.now(), null, 1, null);

        listRecruiterUserRequests.add(recruiterFree);
        listRecruiterUserRequests.add(recruiterGold);
        listRecruiterUserRequests.add(recruiterAdmin);
        return listRecruiterUserRequests;
    }
}
