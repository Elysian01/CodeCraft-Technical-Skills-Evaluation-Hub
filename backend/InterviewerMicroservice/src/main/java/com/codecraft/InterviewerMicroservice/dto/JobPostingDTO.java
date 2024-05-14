package com.codecraft.InterviewerMicroservice.dto;

import com.codecraft.InterviewerMicroservice.entities.Interviewer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingDTO {
    private String JobName;
    private String jobDescription;
    private String status = "open"; // open, close
    private int noOfEnrollments = 0;
    private String roleType;
    private int interviewerId;
}
