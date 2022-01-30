package com.onurkus.graduationproject.message.repository;

import com.onurkus.graduationproject.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    @Transactional
    @Modifying
    @Query(" DELETE FROM Message a WHERE a.identityId= :identityId ")
    void deleteMessageByIdentityId(Long identityId);

    Message findMessageByIdentityId(Long identityId);

}
