package com.iwa.jobservice.jobcategory;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobcategoryService {

    private JobcategoryRepository jobcategoryRepository;

    public JobcategoryService(JobcategoryRepository jobcategoryRepository) {
        this.jobcategoryRepository = jobcategoryRepository;
    }

    public List<Jobcategory> getJobcategories() {
        System.out.println(jobcategoryRepository.findAll());
        return jobcategoryRepository.findAll();
    }

    @Transactional
    public Jobcategory createJobcategory(Jobcategory jobcategory) {
        jobcategoryRepository.save(jobcategory);
        return jobcategory;
    }

    @Transactional
    public List<Jobcategory> createJobcategory(List<Jobcategory> list) {
        jobcategoryRepository.saveAll(list);
        return list;
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (jobcategoryRepository.existsById(id)) {
            jobcategoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Jobcategory updateJobcategory(Long id, Jobcategory jobcategory) {
        if (jobcategoryRepository.existsById(id)) {
            jobcategory.setId(id);
            return jobcategoryRepository.save(jobcategory);
        }
        else {
            return null;
        }
    }

    public Long getNumberOfJobcategories() {
        return jobcategoryRepository.count();
    }

    public Optional<Jobcategory> getJobcategoryById(Long id) {
        return jobcategoryRepository.findById(id);
    }
}
