package com.onurkus.graduationproject.dao;

import com.onurkus.graduationproject.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends JpaRepository<Message,Long> {
}
