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
    public Recruiter updateRecruiter(Long id, Recruiter updatedRecruiter) {
        Recruiter existingRecruiter = recruiterRepository.findById(id).orElse(null);

        if (existingRecruiter != null) {
            updatedRecruiter.setId(id);
            return recruiterRepository.save(updatedRecruiter);
        } else {
            return null;
        }
    }
}
