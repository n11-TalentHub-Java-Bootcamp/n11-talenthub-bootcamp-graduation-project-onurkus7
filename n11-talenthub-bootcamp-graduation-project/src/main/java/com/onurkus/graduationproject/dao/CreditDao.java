package com.onurkus.graduationproject.dao;

import com.onurkus.graduationproject.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditDao extends JpaRepository<Credit,Long> {
}
