package com.iwa.jobservice.recruitment;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentService {

    private RecruitmentRepository repository;

    public RecruitmentService(RecruitmentRepository repository) {
        this.repository = repository;
    }

    public List<Recruitment> getRecruitments() {
        return repository.findAll();
    }

    @Transactional
    public Recruitment create(Recruitment recruitment) {
        repository.save(recruitment);
        return recruitment;
    }

    @Transactional
    public List<Recruitment> create(List<Recruitment> recruitments) {
        repository.saveAll(recruitments);
        return recruitments;
    }

    public Long getNumberOfRecruitments() {
        return repository.count();
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Optional<Recruitment> getById(Long id) {
        return repository.findById(id);
    }

}
