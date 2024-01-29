package com.example.leetcodeclone.submission;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @PreAuthorize("hasAuthority('submission:create')")
    @PostMapping
    public String submit(){
        return "Post submit";
    }
    @PreAuthorize("hasAuthority('submission:read')")
    @GetMapping
    public List<String> getSubmissions(){
        return List.of("Submission 1", "Submission 2");
    }

    @PreAuthorize("hasAuthority('submission:read')")
    @GetMapping("/{id}")
    public String getSubmission(@PathVariable String id){
        return "Submission" + id;
    }
    @PreAuthorize("hasAuthority('submission:update')")
    @PutMapping("/{id}")
    public String updateSubmission(@PathVariable String id){
        return "Update submission" + id;
    }

    @PreAuthorize("hasAuthority('submission:delete')")
    @DeleteMapping("/{id}")
    public String deleteSubmission(@PathVariable String id){
        return "Delete submission" + id;
    }


}
