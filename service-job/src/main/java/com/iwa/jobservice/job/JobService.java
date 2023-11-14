package com.iwa.jobservice.job;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Transactional
    public Job createJob(Job job) {
        jobRepository.save(job);
        return job;
    }

    @Transactional
    public List<Job> createJob(List<Job> list) {
        jobRepository.saveAll(list);
        return list;
    }

    public Long getNumberOfJobs() {
        return jobRepository.count();
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Job updateJob(Long id, Job updatedJob) {
        Job existingJob = jobRepository.findById(id).orElse(null);

        if (existingJob != null) {
            updatedJob.setId(id);
            return jobRepository.save(updatedJob);
        } else {
            return null;
        }
    }

    public Optional<Job> getById(Long id) {
        return jobRepository.findById(id);
    }
}
