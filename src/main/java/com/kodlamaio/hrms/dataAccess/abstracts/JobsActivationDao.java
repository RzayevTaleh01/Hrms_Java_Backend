package com.kodlamaio.hrms.dataAccess.abstracts;

import com.kodlamaio.hrms.entities.concretes.JobsActivation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsActivationDao extends JpaRepository<JobsActivation,Integer> {
}
