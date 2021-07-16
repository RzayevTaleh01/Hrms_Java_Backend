package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.CvPrgSkillsService;
import com.kodlamaio.hrms.dataAccess.abstracts.CvDao;
import com.kodlamaio.hrms.dataAccess.abstracts.CvPrgSkillsDao;
import com.kodlamaio.hrms.entities.concretes.CvPrgSkills;
import com.kodlamaio.hrms.entities.dto.CvPrgSkillsDto;
import com.kodlamaio.hrms.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvPrgSkillsManager implements CvPrgSkillsService {

    private CvPrgSkillsDao cvPrgSkillsDao;
    private CvDao cvDao;

    @Autowired
    public CvPrgSkillsManager(CvPrgSkillsDao cvPrgSkillsDao, CvDao cvDao) {
        this.cvPrgSkillsDao = cvPrgSkillsDao;
        this.cvDao = cvDao;
    }

    @Override
    public Result addSkill(CvPrgSkillsDto cvPrgSkillsDto) {

        if(!this.cvDao.existsById(cvPrgSkillsDto.getCvId())){
            return new ErrorResult("Belə bir Cv mövcud deyildir!");
        }else if(cvPrgSkillsDto.getName().length()<=2){
            return new ErrorResult("Ad 2 simvoldan az ola bilməz!");
        }

        CvPrgSkills cvPrgSkills =new CvPrgSkills();
        cvPrgSkills.setCv(this.cvDao.getById(cvPrgSkillsDto.getCvId()));
        cvPrgSkills.setName(cvPrgSkillsDto.getName());

        this.cvPrgSkillsDao.save(cvPrgSkills);
        return new SuccessResult("Əlavə Olundu!");
    }

    @Override
    public Result removeSkill(int skillId) {
        if(!this.cvPrgSkillsDao.existsById(skillId)){
            return new ErrorResult("Belə bir Cv mövcud deyildir!");
        }
        this.cvPrgSkillsDao.deleteById(skillId);
        return new SuccessResult("Silindi");
    }

    @Override
    public DataResult<List<CvPrgSkills>> getByCvId(int cvId) {
        if(!this.cvDao.existsById(cvId)){
            return new ErrorDataResult<List<CvPrgSkills>>("Belə bir Cv mövcud deyildir!");
        }

        return new SuccessDataResult<List<CvPrgSkills>>(this.cvPrgSkillsDao.findByCvId(cvId),"Data listelendi");
    }
}
