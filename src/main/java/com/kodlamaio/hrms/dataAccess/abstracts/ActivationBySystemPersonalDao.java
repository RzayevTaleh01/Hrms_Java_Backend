package com.kodlamaio.hrms.dataAccess.abstracts;

import com.kodlamaio.hrms.entities.concretes.ActivationBySystemPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationBySystemPersonalDao extends JpaRepository<ActivationBySystemPersonal,Integer> {
    ActivationBySystemPersonal findByEmployeId(int id);

}
