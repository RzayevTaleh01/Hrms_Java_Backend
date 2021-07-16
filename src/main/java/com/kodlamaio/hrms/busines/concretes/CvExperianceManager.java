package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.CvExperianceService;
import com.kodlamaio.hrms.dataAccess.abstracts.CvDao;
import com.kodlamaio.hrms.dataAccess.abstracts.CvExperianceDao;
import com.kodlamaio.hrms.entities.concretes.CvExperiance;
import com.kodlamaio.hrms.entities.dto.CvExperianceDto;
import com.kodlamaio.hrms.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvExperianceManager implements CvExperianceService {

    private CvExperianceDao cvExperianceDao;
    private CvDao cvDao;

    @Autowired
    public CvExperianceManager(CvExperianceDao cvExperianceDao, CvDao cvDao) {
        this.cvExperianceDao = cvExperianceDao;
        this.cvDao=cvDao;
    }

    @Override
    public Result add(CvExperianceDto cvExperianceDto) {

        if(!this.cvDao.existsById(cvExperianceDto.getCvId())){
            return new ErrorResult("Böyle bir cv yok");
        }else if(cvExperianceDto.getCompanyName().length()<=2){
            return new ErrorResult("Şirket adı 2 karakterden uzun olmalıdır");
        }else if(cvExperianceDto.getPosition().length()<=2){
            return new ErrorResult("Pozisyon adı 2 karakterden uzun olmalıdır");
        }else if(cvExperianceDto.getStartDate() == null){
            return new ErrorResult("Başlangıç tarihi boş bırakılamaz");
        }

        CvExperiance cvExperiance =new CvExperiance();
        cvExperiance.setCv(this.cvDao.getById(cvExperianceDto.getCvId()));
        cvExperiance.setCompanyName(cvExperianceDto.getCompanyName());
        cvExperiance.setPosition(cvExperianceDto.getPosition());
        cvExperiance.setStartDate(cvExperianceDto.getStartDate());
        cvExperiance.setEndDate(cvExperianceDto.getEndDate());

        this.cvExperianceDao.save(cvExperiance);
        return new SuccessResult("Kaydedildi");
    }

    @Override
    public Result delete(int experianceId) {
        if(!this.cvExperianceDao.existsById(experianceId)){
            return new ErrorResult("Böyle bir tecrübe yok");
        }
        this.cvExperianceDao.deleteById(experianceId);
        return new SuccessResult("Silindi");
    }

    @Override
    public DataResult<List<CvExperiance>> getByCvId(int id) {

        return new SuccessDataResult<List<CvExperiance>>(this.cvExperianceDao.findByCvId(id),"Data listelendi");
    }
}
