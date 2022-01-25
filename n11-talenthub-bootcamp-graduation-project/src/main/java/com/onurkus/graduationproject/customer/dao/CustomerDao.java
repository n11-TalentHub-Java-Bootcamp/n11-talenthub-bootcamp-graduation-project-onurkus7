package com.onurkus.graduationproject.customer.dao;

import com.onurkus.graduationproject.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Long> {

    Customer findByIdentityId(Long identityId);
    
}
