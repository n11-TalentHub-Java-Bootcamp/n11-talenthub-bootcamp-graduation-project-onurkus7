package com.onurkus.graduationproject.credit.dao;

import com.onurkus.graduationproject.credit.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditDao extends JpaRepository<Credit,Long> {
}
