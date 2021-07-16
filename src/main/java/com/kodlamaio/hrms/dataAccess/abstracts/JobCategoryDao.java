package com.kodlamaio.hrms.dataAccess.abstracts;

import com.kodlamaio.hrms.entities.concretes.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCategoryDao extends JpaRepository<JobCategory,Integer> {
    JobCategory findByName(String name);
}
