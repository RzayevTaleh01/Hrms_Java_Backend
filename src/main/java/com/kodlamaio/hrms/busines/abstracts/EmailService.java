package com.kodlamaio.hrms.busines.abstracts;

import com.kodlamaio.hrms.entities.concretes.User;

public interface EmailService {
    void sendVerifyEmail(User user,String code);
}
