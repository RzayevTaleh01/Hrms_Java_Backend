package com.kodlamaio.hrms.dataAccess.abstracts;

import com.kodlamaio.hrms.entities.concretes.CvImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CvImgDao extends JpaRepository<CvImg,Integer> {
    List<CvImg> findByOrderById();
    CvImg findByCvId(int id);
}
