package com.codecraft.InterviewerMicroservice.services;

import com.codecraft.InterviewerMicroservice.dto.AppliedJobDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8082",value = "AppliedJobs-Client")
public interface CandidateClient {
    @GetMapping("/appliedJobs/{id}")
    ResponseEntity<List<AppliedJobDTO>> listOfAppliedJobs(@PathVariable Integer id);
}
