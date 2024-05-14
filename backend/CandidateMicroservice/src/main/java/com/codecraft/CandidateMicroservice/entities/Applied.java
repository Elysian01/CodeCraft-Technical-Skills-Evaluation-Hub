package com.codecraft.CandidateMicroservice.entities;


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
public class Applied {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid", referencedColumnName = "id")
    private Candidate candidate;

    private Integer jid;
    private String job_name;
    private String test_score;
    private String applied_status; // success, pending, rejected
}

