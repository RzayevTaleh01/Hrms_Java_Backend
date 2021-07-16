package com.kodlamaio.hrms.busines.concretes;

import com.kodlamaio.hrms.busines.abstracts.CvImgService;
import com.kodlamaio.hrms.core.services.CloudinaryService;
import com.kodlamaio.hrms.dataAccess.abstracts.CvDao;
import com.kodlamaio.hrms.dataAccess.abstracts.CvImgDao;
import com.kodlamaio.hrms.entities.concretes.CvImg;
import com.kodlamaio.hrms.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CvImgManager implements CvImgService {

    private CvImgDao cvImgDao;
    private CloudinaryService cloudinaryService;
    private CvDao cvDao;

    @Autowired
    public CvImgManager(CvImgDao cvImgDao, CloudinaryService cloudinaryService, CvDao cvDao) {
        this.cvImgDao = cvImgDao;
        this.cloudinaryService=cloudinaryService;
        this.cvDao=cvDao;
    }

    @Override
    public DataResult<List<CvImg>> getAll() {
        return new SuccessDataResult<List<CvImg>>(this.cvImgDao.findByOrderById(),"Data listelendi");
    }

    @Override
    public Result update(MultipartFile multipartFile, int cvId) {
        try {
            BufferedImage bufferedImage= ImageIO.read(multipartFile.getInputStream());
            if(bufferedImage==null){
                return new ErrorResult("Resim validasyonu başarısız");
            }
        }catch (IOException exception){
            return new ErrorResult("Resim validasyonu başarısız");
        }
        CvImg cvImg =this.cvImgDao.findByCvId(cvId);
        if(cvImg.getImageId()==null){
            try {
                Map result=cloudinaryService.upload(multipartFile);
                cvImg.setName((String)result.get("original_filename"));
                cvImg.setImageUrl((String)result.get("url"));
                cvImg.setImageId((String)result.get("public_id"));
                this.cvImgDao.save(cvImg);
                return new SuccessResult("Başarıyla eklendi");
            }catch (IOException exception){
                return new ErrorResult("Resim yükleme aşamasında bir sorun oldu");
            }
        }else if(cvImg.getImageId()!=null){
            //claudanry silme
            try {
                Map result=cloudinaryService.delete(cvImg.getImageId());
                Map result2=cloudinaryService.upload(multipartFile);
                cvImg.setName((String)result.get("original_filename"));
                cvImg.setImageUrl((String)result.get("url"));
                cvImg.setImageId((String)result.get("public_id"));
                this.cvImgDao.save(cvImg);
                return new SuccessResult("Başarıyla yüklendi");
            }catch (IOException exception){
                return new ErrorResult("Resim yükleme aşamasında bir sorun oldu");
            }
        }else{
            return new ErrorResult("Bir sorun var lütfen iletişime mail at");
        }
    }

    @Override
    public Result delete(int id) {
        if(!this.cvImgDao.existsById(id)){
            return new ErrorResult("Böyle bir resim bulunamadı");
        }
        try {
            CvImg cvImg =this.cvImgDao.getById(id);
            Map result=cloudinaryService.delete(cvImg.getImageId());
            cvImg.setName(null);
            cvImg.setImageId(null);
            cvImg.setImageUrl("https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg");
            this.cvImgDao.save(cvImg);
            return new SuccessResult("Resim başarıyla silindi");
        }catch (IOException exception){
            return new ErrorResult("Bir hata olştu");
        }
    }

    @Override
    public DataResult<CvImg> getById(int id) {
        if(!this.cvImgDao.existsById(id)){
            return new ErrorDataResult<CvImg>("Bu idye ait resim bulunamadı");
        }
        return new SuccessDataResult<CvImg>(this.cvImgDao.getById(id),"Verilen id ye ait resim listelendi");
    }

    @Override
    public Boolean isExist(int id) {
        return this.cvImgDao.existsById(id);
    }
}
