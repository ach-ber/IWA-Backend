package com.iwa.jobservice.jobcategory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobcategory")
public class JobcategoryController {

    private final JobcategoryService service;

    public JobcategoryController(JobcategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Jobcategory> getAllCategories() {
        return service.getJobcategories();
    }

}
