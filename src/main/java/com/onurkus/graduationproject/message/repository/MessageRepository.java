package com.onurkus.graduationproject.message.repository;

import com.onurkus.graduationproject.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {



}
