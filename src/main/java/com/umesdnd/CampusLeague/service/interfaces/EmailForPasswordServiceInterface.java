package com.umesdnd.CampusLeague.service.interfaces;


import com.umesdnd.CampusLeague.model.DTO.EmailForPassword;

public interface EmailForPasswordServiceInterface {
    boolean sendEmail(EmailForPassword email);
}
