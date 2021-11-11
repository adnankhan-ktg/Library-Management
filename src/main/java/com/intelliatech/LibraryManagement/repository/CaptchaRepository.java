package com.intelliatech.LibraryManagement.repository;

import com.intelliatech.LibraryManagement.model.Captcha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptchaRepository extends JpaRepository<Captcha, Long> {
 public Captcha findByCaptchaCodeId(long captchaCodeId);
}
