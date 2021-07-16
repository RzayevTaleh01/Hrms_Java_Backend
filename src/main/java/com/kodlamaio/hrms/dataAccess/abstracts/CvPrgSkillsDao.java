package com.kodlamaio.hrms.dataAccess.abstracts;

import com.kodlamaio.hrms.entities.concretes.CvPrgSkills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CvPrgSkillsDao extends JpaRepository<CvPrgSkills,Integer> {
    List<CvPrgSkills> findByCvId(int id);
}
