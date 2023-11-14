package com.iwa.jobservice.recruitment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruitment")
public class RecruitmentController {

    private final RecruitmentService service;

    public RecruitmentController(RecruitmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Recruitment> getRecruitments() {
        return service.getRecruitments();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Recruitment> getRecruitment(@PathVariable Long id) {
        return service.getById(id)
                .map(recruitment -> ResponseEntity.status(HttpStatus.OK).body(recruitment))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recruitment createRecruitment(@RequestBody final Recruitment recruitment) {
        return service.create(recruitment);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Recruitment> delete(@PathVariable Long id) {
        boolean deleted = service.deleteById(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recruitment> updateRecruitment(@PathVariable Long id, @RequestBody Recruitment updatedRecruitment) {
        Recruitment result = service.update(id, updatedRecruitment);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

