package com.onurkus.graduationproject.credit.repository;

import com.onurkus.graduationproject.credit.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    @Query("SELECT a FROM Credit a, Customer b WHERE a.identityId= b.identityId AND a.identityId= :identityId AND" +
            " b.birthdayDate= :birthdayDate ORDER BY a.creationDate DESC")
    List<Credit> findCreditByIdentityIdAndBirthdayDate(Long identityId, Date birthdayDate);

}
