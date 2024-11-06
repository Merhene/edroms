package com.merhene.edroms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.merhene.edroms.model.Guard;

public interface GuardRepository extends JpaRepository<Guard, Long> {
}
