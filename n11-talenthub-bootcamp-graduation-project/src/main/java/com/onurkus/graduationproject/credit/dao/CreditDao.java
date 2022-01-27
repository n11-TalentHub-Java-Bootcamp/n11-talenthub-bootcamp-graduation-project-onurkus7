package com.onurkus.graduationproject.credit.dao;

import com.onurkus.graduationproject.credit.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface CreditDao extends JpaRepository<Credit,Long> {

    @Query("SELECT a.creditLimit FROM Credit a, Customer b WHERE a.identityId= b.identityId AND a.identityId= :identityId AND b.birthdayDate= :birthdayDate ORDER BY a.creationDate DESC")
    List<BigDecimal> findCreditByIdentityIdAndBirthdayDate(Long identityId, Date birthdayDate);

}
