package com.iwa.recruiterservice.recruiter;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {
    private RecruiterRepository recruiterRepository;

    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Transactional
    public Recruiter createRecruiter(Recruiter recruiter){
        return this.recruiterRepository.save(recruiter);
    }

    public Long getNumberOfRecruiters(){
        return this.recruiterRepository.count();
    }

    public List<Recruiter> getRecruiters(){
        return this.recruiterRepository.findAll();
    }

    public Recruiter getRecruiterById(Long id){
        return this.recruiterRepository.findById(id).orElse(null);
    }

    public void deleteRecruiterById(Long id){
        this.recruiterRepository.deleteById(id);
    }

}
