package com.iwa.recruiterservice.recruiter;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class RecruiterService {

    @Value("${service.user.url}")
    private String userServiceUrl;

    private RecruiterRepository recruiterRepository;

    @Autowired
    private RestTemplate restTemplate;

    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }


    public ResponseEntity<?> createRecruiter(RecruiterUserRequest recruiterUserRequest){
        System.out.println("createRecruiter");
        ResponseEntity<Recruiter> response;
        Recruiter recruiter = new Recruiter(recruiterUserRequest);
        User user = new User(recruiterUserRequest);
        System.out.println("recruiter and user created");
        try {
            System.out.println("trying to add recruiter");
            response = new ResponseEntity<>(addRecruiter(recruiter), HttpStatus.CREATED);
            try {
                System.out.println("trying to add user");
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                Map<String, String> requestBody = new HashMap<>();
                requestBody.put("email", user.getEmail());
                requestBody.put("role", user.getRole());
                requestBody.put("password", user.getPassword());
                HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
                System.out.println("request:" + request);
                restTemplate.postForObject(userServiceUrl + "/auth/register", request, String.class);
            } catch (Exception e) {
                recruiterRepository.deleteById(recruiter.getId());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while creating user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while creating recruiter");
        }
        return response;
    }

    public String test(){
        ResponseEntity<String> response = restTemplate.getForEntity("http://service-user:7000/auth/testss", String.class);
        return response.getBody();
    }

    public Long getNumberOfRecruiters(){
        return this.recruiterRepository.count();
    }

    public List<Recruiter> getRecruiters(){
        return this.recruiterRepository.findAll();
    }

    public Optional<Recruiter> getRecruiterById(Long id){
        return this.recruiterRepository.findById(id);
    }

    @Transactional
    public boolean deleteRecruiterById(Long id){
        if (recruiterRepository.existsById(id)) {
            recruiterRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Recruiter> updateRecruiter(Long id, Recruiter updatedRecruiter) {
        Optional<Recruiter> existingRecruiter = recruiterRepository.findById(id);

        if (existingRecruiter.isPresent()) {
            updatedRecruiter.setId(id);
            return Optional.of(recruiterRepository.save(updatedRecruiter));
        } else {
            return Optional.empty();
        }
    }

    public Recruiter addRecruiter(Recruiter newRecruiter) throws Exception {
        Optional<Recruiter> existingRecruiter = recruiterRepository.findByEmail(newRecruiter.getEmail());
        if (existingRecruiter.isPresent()) {
            throw new Exception("Recruiter already exists");
        } else {
            return recruiterRepository.save(newRecruiter);
        }
    }
}
