package com.iwa.jobservice.jobcategory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobcategories")
public class JobcategoryController {

    private final JobcategoryService service;

    public JobcategoryController(JobcategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Jobcategory> getAllCategories() {
        return service.getJobcategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jobcategory> getJobcategoryById(@PathVariable Long id) {
        return service.getJobcategoryById(id)
            .map(jobcategory -> new ResponseEntity<>(jobcategory, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Jobcategory createJobcategory(Jobcategory jobcategory) {
        return service.createJobcategory(jobcategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jobcategory> updateJobcategory(@PathVariable Long id, @RequestBody Jobcategory jobcategory) {
        return service.updateJobcategory(id, jobcategory)
            .map(updatedJobcategory -> new ResponseEntity<>(updatedJobcategory, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobcategory(@PathVariable Long id) {
        boolean deleted = service.deleteById(id);

        if (deleted) {
            return new ResponseEntity<>("Jobcategory deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Jobcategory not found", HttpStatus.NOT_FOUND);
        }
    }

}
