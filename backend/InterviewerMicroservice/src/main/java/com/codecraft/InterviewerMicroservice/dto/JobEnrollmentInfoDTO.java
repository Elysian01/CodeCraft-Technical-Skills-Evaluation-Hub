package com.codecraft.InterviewerMicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobEnrollmentInfoDTO {
    private Long candidateId;
    private String candidateName;
    private String testScore;
    private Long jobId;
    private Long interviewRecordId;
}
