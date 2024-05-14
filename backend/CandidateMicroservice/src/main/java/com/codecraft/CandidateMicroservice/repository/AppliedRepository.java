package com.codecraft.CandidateMicroservice.repository;

import com.codecraft.CandidateMicroservice.entities.Applied;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppliedRepository extends JpaRepository<Applied, Integer> {
}
