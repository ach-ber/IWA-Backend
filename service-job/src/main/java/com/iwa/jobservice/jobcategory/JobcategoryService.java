package com.iwa.jobservice.jobcategory;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Long getNumberOfJobcategories() {
        return jobcategoryRepository.count();
    }
}
