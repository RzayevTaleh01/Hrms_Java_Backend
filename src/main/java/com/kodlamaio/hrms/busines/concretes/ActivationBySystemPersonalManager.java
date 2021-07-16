package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.ActivationBySystemPersonalService;
import com.kodlamaio.hrms.core.utilities.results.ErrorResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.core.utilities.results.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.ActivationBySystemPersonalDao;
import com.kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrms.entities.concretes.ActivationBySystemPersonal;
import com.kodlamaio.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
public class ActivationBySystemPersonalManager implements ActivationBySystemPersonalService {

    private ActivationBySystemPersonalDao activationBySystemPersonalDao;
    private EmployerDao employerDao;

    @Autowired
    public ActivationBySystemPersonalManager(ActivationBySystemPersonalDao activationBySystemPersonalDao, EmployerDao employerDao) {
        this.activationBySystemPersonalDao = activationBySystemPersonalDao;
        this.employerDao=employerDao;
    }

    @Override
    public void createActivationByStaff(Employer employer) {
        ActivationBySystemPersonal activationBySystemPersonal =new ActivationBySystemPersonal();
        activationBySystemPersonal.setEmployeId(employer.getId());
        activationBySystemPersonal.setVerifyed(false);
        activationBySystemPersonal.setStaffId(null);
        activationBySystemPersonalDao.save(activationBySystemPersonal);
    }

    @Override
    public Result activateEmployer(int employerId, int staffId) {

        try {
            Employer employer = employerDao.getById(employerId);
            ActivationBySystemPersonal activationBySystemPersonal = activationBySystemPersonalDao.findByEmployeId(employerId);

            employer.setActive(true);
            employerDao.save(employer);

            activationBySystemPersonal.setVerifyed(true);
            activationBySystemPersonal.setVerifyDate(LocalDate.now());
            activationBySystemPersonal.setStaffId(staffId);
            activationBySystemPersonalDao.save(activationBySystemPersonal);

        }catch (EntityNotFoundException exception){
            return new ErrorResult("Səhv İd");
        }
        return new SuccessResult("İstifadəçi aktiv edildi");
    }
}
