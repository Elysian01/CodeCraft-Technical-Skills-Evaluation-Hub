package com.codecraft.InterviewerMicroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobName;
    private String jobDescription;
    private String status;
    private int noOfEnrollments;
    private String roleType;

    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    private Interviewer interviewer;
}
