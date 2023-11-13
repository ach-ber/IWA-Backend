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
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

}

