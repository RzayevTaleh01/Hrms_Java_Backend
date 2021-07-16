package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.SystemPersonalService;
import com.kodlamaio.hrms.busines.abstracts.UserService;
import com.kodlamaio.hrms.dataAccess.abstracts.SystemPersonalDao;
import com.kodlamaio.hrms.entities.concretes.SystemPersonal;
import com.kodlamaio.hrms.entities.dto.SystemPersonalUpdateDto;
import com.kodlamaio.hrms.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class SystemPersonalManager implements SystemPersonalService {

    private SystemPersonalDao systemPersonalDao;
    private UserService userService;

    @Autowired
    public SystemPersonalManager(SystemPersonalDao systemPersonalDao, UserService userService) {
        this.systemPersonalDao = systemPersonalDao;
        this.userService=userService;
    }

    @Override
    public Result create(SystemPersonal systemPersonal) {
        if(systemPersonal.getPassword().length() <=6){
            return new ErrorResult("Şifre 6 karakterden uzun olmalıdır");
        }else if(!isEmailValid(systemPersonal.getEmail())){
            return new ErrorResult("Email geçerli formatta değil");
        }else if(userService.getByEmail(systemPersonal.getEmail()).getData() != null){
            return new ErrorResult("Bu email zaten kayıtlı");
        }
        systemPersonal.setMailVerify(true);
        systemPersonalDao.save(systemPersonal);
        return new SuccessResult("Kayıt yapıldı");
    }

    @Override
    public DataResult<List<SystemPersonal>> getAll() {
        return new SuccessDataResult<List<SystemPersonal>>(this.systemPersonalDao.findAll(),"Data listelendi");
    }

    @Override
    public Result update(SystemPersonalUpdateDto systemPersonalUpdateDto) {

        if(!this.systemPersonalDao.existsById(systemPersonalUpdateDto.getStaffId())){
            return new ErrorResult("Böyle bir personel yok");
        }else if(systemPersonalUpdateDto.getFirstName().length()<2){
            return new ErrorResult("İsim 2 karakterden kısa olamaz");
        }else if(systemPersonalUpdateDto.getLastName().length()<2){
            return new ErrorResult("Soy isim 2 karakterden kısa olamaz");
        }else if(!isEmailValid(systemPersonalUpdateDto.getEmail())){
            return new ErrorResult("Geçerli bir email değil");
        }

        SystemPersonal systemPersonal =this.systemPersonalDao.getById(systemPersonalUpdateDto.getStaffId());
        systemPersonal.setFirstName(systemPersonalUpdateDto.getFirstName());
        systemPersonal.setLastName(systemPersonalUpdateDto.getLastName());
        systemPersonal.setEmail(systemPersonalUpdateDto.getEmail());
        this.systemPersonalDao.save(systemPersonal);
        return new SuccessResult("Bilgiler kaydedildi");
    }

    private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    public boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }
}
