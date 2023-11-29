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

        if (addressService.getNumberOfAddresses() < 1) {
            addressService.createAddress(new Address("1", "rue de la paix", "apt 12", "Paris", "75000", "France"));
            addressService.createAddress(new Address("2", "rue de la république", "apt 34", "Montpellier", "34000", "France"));
            addressService.createAddress(new Address("3", "rue des champs élysées", "", "Paris", "75000", "France"));
        } else {
            System.out.println("Addresses already initialized");
        }

        if (establishmentService.getNumberOfEstablishments() < 1) {
            establishmentService.createEstablishment(new Establishment("McDonald's Champs Elysée", 123456789_00001L, 3L));
            establishmentService.createEstablishment(new Establishment("McDonald's Montpellier", 123456789_00002L, 2L));
            establishmentService.createEstablishment(new Establishment("KFC Paris", 987654321_00001L, 1L));
        } else {
            System.out.println("Establishments already initialized");
        }

        if (companyService.getNumberOfCompanies() < 1) {
            companyService.createCompany(new Company("McDonald's", 123456789L, new Long[]{1L, 2L}));
            companyService.createCompany(new Company("KFC", 987654321L, new Long[]{3L}));
        } else {
            System.out.println("Companies already initialized");
        }
    }
    private static List<RecruiterUserRequest> getRecruiterUserList() {
        List<RecruiterUserRequest> listRecruiterUserRequests = new ArrayList<>();
        RecruiterUserRequest recruiterFree = new RecruiterUserRequest(
                "Free", "User", "free", "0600000000", "free@gmail.com",
                LocalDate.now(), "ROLE_FREE",
                LocalDate.now(), null, 1L, new Long[]{1L, 2L});

        RecruiterUserRequest recruiterAdmin = new RecruiterUserRequest(
                "admin", "admin", "admin", "0600000000", "admin@gmail.com",
                LocalDate.now(), "ROLE_ADMIN",
                LocalDate.now(), null, 2L, new Long[]{3L});

        listRecruiterUserRequests.add(recruiterFree);
        listRecruiterUserRequests.add(recruiterAdmin);
        return listRecruiterUserRequests;
    }
}
