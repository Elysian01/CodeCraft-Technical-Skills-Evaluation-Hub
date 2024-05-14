package com.codecraft.InterviewerMicroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @ManyToMany
    @JoinTable(name = "job_questions",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questions = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "job_requirements",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "requirement_id"))
    private Set<AllRequirements> allRequirements = new HashSet<>();
}