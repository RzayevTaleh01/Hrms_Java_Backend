package com.kodlamaio.hrms.dataAccess.abstracts;

import com.kodlamaio.hrms.entities.concretes.Jobs;
import com.kodlamaio.hrms.entities.dto.JobsFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobsDao extends JpaRepository<Jobs,Integer> {
    List<Jobs> findByActive(boolean active);
    List<Jobs> findByActiveOrderByLastDate(boolean active);
    List<Jobs> findByActiveTrueAndEmployer_Id(int id);


    @Query("Select j from com.kodlamaio.hrms.entities.concretes.Jobs j where ((:#{#filter.cityId}) IS NULL OR j.city.id IN (:#{#filter.cityId}))"
        +" and ((:#{#filter.jobCategoryId}) IS NULL OR j.jobCategory.id IN (:#{#filter.jobCategoryId}))"
        +" and ((:#{#filter.workPlaceId}) IS NULL OR j.workPlace.id IN (:#{#filter.workPlaceId}))"
        +" and ((:#{#filter.workTimeId}) IS NULL OR j.workTime.id IN (:#{#filter.workTimeId}))"
        +" and j.active=true")
    public Page<Jobs> getByFilter(@Param("filter") JobsFilter jobsFilter, Pageable pageable);
}
