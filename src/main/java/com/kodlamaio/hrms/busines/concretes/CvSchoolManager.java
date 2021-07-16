package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.CvSchoolService;
import com.kodlamaio.hrms.dataAccess.abstracts.CvDao;
import com.kodlamaio.hrms.dataAccess.abstracts.CvSchoolDao;
import com.kodlamaio.hrms.entities.concretes.CvSchool;
import com.kodlamaio.hrms.entities.dto.CvSchoolDto;
import com.kodlamaio.hrms.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvSchoolManager implements CvSchoolService {

    private CvSchoolDao cvSchoolDao;
    private CvDao cvDao;

    @Autowired
    public CvSchoolManager(CvSchoolDao cvSchoolDao, CvDao cvDao) {
        this.cvSchoolDao = cvSchoolDao;
        this.cvDao=cvDao;
    }

    @Override
    public Result addSchool(CvSchoolDto cvSchoolDto) {

        if(!this.cvDao.existsById(cvSchoolDto.getCvId())){
            return new ErrorResult("Böyle bir cv yok");
        }else if(cvSchoolDto.getName().length()<=2){
            return new ErrorResult("Okul adı 2 karakterden uzun olmalıdır");
        }else if(cvSchoolDto.getDepartment().length()<=2){
            return new ErrorResult("Bölüm adı 2 karakterden uzun olmalıdır");
        }else if(cvSchoolDto.getStartDate()==null){
            return new ErrorResult("Başlangıç tarihi boş birakılamaz");
        }

        CvSchool cvSchool =new CvSchool();
        cvSchool.setCv(this.cvDao.getById(cvSchoolDto.getCvId()));
        cvSchool.setName(cvSchoolDto.getName());
        cvSchool.setDepartment(cvSchoolDto.getDepartment());
        cvSchool.setStartDate(cvSchoolDto.getStartDate());
        cvSchool.setEndDate(cvSchoolDto.getEndDate());

        this.cvSchoolDao.save(cvSchool);
        return new SuccessResult("Okul eklendi");
    }

    @Override
    public Result deleteSchool(int schoolId) {
        if(!this.cvSchoolDao.existsById(schoolId)){
            return new ErrorResult("Böyle bir okul yok");
        }
        this.cvSchoolDao.deleteById(schoolId);
        return new SuccessResult("Okul silindi");
    }

    @Override
    public DataResult<List<CvSchool>> getByCvId(int cvId) {
        if(this.cvSchoolDao.findByCvId(cvId)==null){
            return new ErrorDataResult<List<CvSchool>>("Böyle bir cv yok");
        }
        return new SuccessDataResult<List<CvSchool>>(this.cvSchoolDao.findByCvId(cvId),"Data listelendi");
    }
}
