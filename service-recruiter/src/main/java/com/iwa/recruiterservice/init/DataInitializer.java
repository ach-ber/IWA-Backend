package com.iwa.recruiterservice.init;

import com.iwa.recruiterservice.address.Address;
import com.iwa.recruiterservice.address.AddressService;
import com.iwa.recruiterservice.recruiter.Recruiter;
import com.iwa.recruiterservice.recruiter.RecruiterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RecruiterService recruiterService;
    private final AddressService addressService;

    public DataInitializer(RecruiterService recruiterService, AddressService addressService) {
        this.recruiterService = recruiterService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
