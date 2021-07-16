package com.kodlamaio.hrms.dataAccess.abstracts;

import com.kodlamaio.hrms.entities.concretes.JobFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobFavoritesDao extends JpaRepository<JobFavorites,Integer> {
    List<JobFavorites> findByEmployeeId(int id);
    boolean existsByEmployee_IdAndJobs_Id(int employeeId, int JobAdId);
}
