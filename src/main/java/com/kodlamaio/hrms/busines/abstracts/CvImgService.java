package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.core.utilities.results.DataResult;
import com.kodlamaio.hrms.core.utilities.results.Result;
import com.kodlamaio.hrms.entities.concretes.CvImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CvImgService {
    DataResult<List<CvImg>> getAll();
    Result update(MultipartFile multipartFile,int cvId);
    Result delete(int id);
    DataResult<CvImg> getById(int id);
    Boolean isExist(int id);
}
