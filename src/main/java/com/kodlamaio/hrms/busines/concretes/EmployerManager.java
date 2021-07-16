package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrms.dataAccess.abstracts.EmployerUpdateDao;
import com.kodlamaio.hrms.dataAccess.abstracts.SystemPersonalDao;
import com.kodlamaio.hrms.entities.concretes.Employer;
import com.kodlamaio.hrms.entities.concretes.EmployerUpdate;
import com.kodlamaio.hrms.entities.dto.EmployerRegisterDto;
import com.kodlamaio.hrms.busines.abstracts.*;
import com.kodlamaio.hrms.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmployerManager implements EmployerService {


    private EmployerDao employerDao;
    private UserService userService;
    private ActivationBySystemPersonalService activationBySystemPersonalService;
    private ActivationCodeService activationCodeService;
    private EmailService emailService;
    private EmployerUpdateDao employerUpdateDao;
    private SystemPersonalDao systemPersonalDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao, UserService userService, ActivationBySystemPersonalService activationBySystemPersonalService, ActivationCodeService activationCodeService, EmailService emailService, EmployerUpdateDao employerUpdateDao, SystemPersonalDao systemPersonalDao) {
        this.employerDao = employerDao;
        this.userService=userService;
        this.activationBySystemPersonalService = activationBySystemPersonalService;
        this.activationCodeService=activationCodeService;
        this.emailService=emailService;
        this.employerUpdateDao=employerUpdateDao;
        this.systemPersonalDao = systemPersonalDao;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Data listelendi");
    }

    @Override
    public DataResult<Employer> getByEmail(String email) {
        return new SuccessDataResult<Employer>(this.employerDao.findByEmail(email),"Listelendi");
    }

    @Override
    public Result add(EmployerRegisterDto employerDto) {
        if(!employerDto.getPassword().equals(employerDto.getRePassword())){
            return new ErrorResult("Şifreler eşleşmiyor");
        }
        Employer employer=new Employer();
        employer.setEmail(employerDto.getEmail());
        employer.setPassword(employerDto.getPassword());
        employer.setCompanyName(employerDto.getCompanyName());
        employer.setWebSite(employerDto.getWebSite());
        employer.setPhoneNumber(employerDto.getPhoneNumber());
        employer.setWaitingUpdate(false);


       if(userService.getByEmail(employer.getEmail()).getData() != null){
            return new ErrorResult("Bu email zaten kayıtlı");
       }else if(!isEmailValid(employer.getEmail())){
           return new ErrorResult("Geçerli bir email giriniz");
       }else if(!employer.getEmail().endsWith(employer.getWebSite())){
           return new ErrorResult("Web siteniz ve emailinizin domaini aynı olmalıdır");
       }else if(employer.getPassword().length() <=6 ){
           return new ErrorResult("Şifre 6 karakterden uzun olmalıdır.");
       }else if(employer.getPhoneNumber().length() <10){
           return new ErrorResult("Geçerli bir telefon numarası giriniz.");
       }else if(employer.getCompanyName().length()<=2){
           return new ErrorResult("Şirket adı 2 karakterden uzun olmalıdır");
       }

       employer.setActive(false);
       employer.setMailVerify(false);
       this.employerDao.save(employer);

       this.emailService.sendVerifyEmail(employer,this.activationCodeService.createActivationCode(employer));
       activationBySystemPersonalService.createActivationByStaff(employer);



       return new SuccessResult(employer.getEmail()+" Adresine doğrulama kodunuz gönderildi");

    }

    @Override
    public DataResult<Employer> getById(int id) {
        if(!this.employerDao.existsById(id)){
            return new ErrorDataResult<Employer>("Böyle bir işveren yok");
        }
        return new SuccessDataResult<Employer>(this.employerDao.getById(id),"Data listelendi");
    }

    @Override
    public Result update(EmployerUpdate employerUpdate) {
        employerUpdate.setId(0);
        employerUpdate.setCreateDay(LocalDate.now());

        if(employerUpdate.getCompanyName().length()<2){
            return new ErrorResult("Şirket adı 2 karakterden kısa olamaz");
        }else if(employerUpdate.getPhoneNumber().length()!=11){
            return new ErrorResult("Telefon numarası 11 haneli olmalıdır");
        }else if(!isEmailValid(employerUpdate.getEmail())){
            return new ErrorResult("Geçerli bir mail adresi giriniz");
        }else if(this.employerDao.existsById(employerUpdate.getEmployerId())){
            return new ErrorResult(("Böyle bir işveren yok"));
        }
        Employer employer=this.employerDao.getById(employerUpdate.getEmployerId());
        this.employerUpdateDao.save(employerUpdate);
        employer.setWaitingUpdate(true);
        this.employerDao.save(employer);
        return new SuccessResult("Güncelleme isteği gönderildi personelin onayı ardından görünür olacaktır");
    }

    @Override
    public Result verifyUpdate(int employerUpdateId, int staffId) {
        if(!this.employerUpdateDao.existsById(employerUpdateId)){
            return new ErrorResult("Böyle bir güncelleme talebi yok");
        }else if(!this.systemPersonalDao.existsById(staffId)){
            return new ErrorResult("Böyle bir personel yok");
        }
        EmployerUpdate employerUpdate=this.employerUpdateDao.getById(employerUpdateId);
        Employer employer=this.employerDao.getById(employerUpdate.getEmployerId());

        employerUpdate.setVerifyed(true);
        employerUpdate.setStaffId(staffId);
        employerUpdate.setVerifyDate(LocalDate.now());
        this.employerUpdateDao.save(employerUpdate);

        employer.setEmail(employerUpdate.getEmail());
        employer.setCompanyName(employerUpdate.getCompanyName());
        employer.setPhoneNumber(employerUpdate.getPhoneNumber());
        employer.setWebSite(employerUpdate.getWebSite());
        employer.setWaitingUpdate(false);
        this.employerDao.save(employer);
        return new SuccessResult("Bilgiler güncellendi");
    }


    private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    public boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }



}
