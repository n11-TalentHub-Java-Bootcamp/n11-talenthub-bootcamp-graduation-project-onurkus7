package com.onurkus.graduationproject.message.dao;

import com.onurkus.graduationproject.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends JpaRepository<Message,Long> {

    Message findMessageByIdentityId(Long identityId);

}
