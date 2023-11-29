package com.iwa.recruiterservice.init;
import com.iwa.recruiterservice.address.Address;
import com.iwa.recruiterservice.address.AddressService;
import com.iwa.recruiterservice.company.Company;
import com.iwa.recruiterservice.company.CompanyService;
import com.iwa.recruiterservice.establishment.Establishment;
import com.iwa.recruiterservice.establishment.EstablishmentService;

import com.iwa.recruiterservice.recruiter.RecruiterService;
import com.iwa.recruiterservice.recruiter.RecruiterUserRequest;
import jakarta.transaction.Transactional;
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

    private final CompanyService companyService;

    public DataInitializer(RecruiterService recruiterService, AddressService addressService, EstablishmentService establishmentService, CompanyService companyService) {
        this.recruiterService = recruiterService;
        this.addressService = addressService;
        this.establishmentService = establishmentService;
        this.companyService = companyService;
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

        addressService.deleteAll();
        if (addressService.getNumberOfAddresses() < 5) {
            addressService.createAddress(new Address(1L, "123", "Main Street", "Apt 456", "Gilles-la-Forêt", "39119", "Îles Mineures Éloignées des États-Unis"));
            addressService.createAddress(new Address(2L, "1", "Place de Londres", "", "Montpellier", "34000", "France"));
            addressService.createAddress(new Address(3L, "1", "Place Eugene Bataillon", "Polytech, batiment 36", "Montpellier", "34000", "France"));
            addressService.createAddress(new Address(4L, "1", "rue connue", "Polytech", "Paris", "75000", "France"));
            addressService.createAddress(new Address(5L, "1", "Avenue des Champs Élysées", "", "Paris", "75000", "France"));
        } else {
            System.out.println("Addresses already initialized");
        }

        establishmentService.deleteAll();
        if (establishmentService.getNumberOfEstablishments() < 4) {
            establishmentService.createEstablishment(new Establishment(1L, "McDonalds Odysseum", 123456789_00001L, 2L));
            establishmentService.createEstablishment(new Establishment(2L, "McDonalds Champs", 123456780_00002L, 5L));
            establishmentService.createEstablishment(new Establishment(3L, "Polytech Montpellier", 123456780_00001L, 3L));
            establishmentService.createEstablishment(new Establishment(4L, "Polytech Paris", 123456780_00002L, 4L));
        } else {
            System.out.println("Establishments already initialized");
        }

        companyService.deleteAll();
        if (companyService.getNumberOfCompanies() < 2) {
            companyService.createCompany(new Company("McDonalds", 123456789L, new Long[]{1L, 2L}));
            companyService.createCompany(new Company("Polytech", 123456780L, new Long[]{3L, 4L}));
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
