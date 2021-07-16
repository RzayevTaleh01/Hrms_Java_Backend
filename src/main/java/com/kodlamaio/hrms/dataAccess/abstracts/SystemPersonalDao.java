package com.kodlamaio.hrms.dataAccess.abstracts;

import com.kodlamaio.hrms.entities.concretes.SystemPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemPersonalDao extends JpaRepository<SystemPersonal,Integer> {
}
