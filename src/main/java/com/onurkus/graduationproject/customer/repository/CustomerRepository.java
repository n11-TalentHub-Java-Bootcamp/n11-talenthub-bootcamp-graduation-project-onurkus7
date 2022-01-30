package com.onurkus.graduationproject.customer.repository;

import com.onurkus.graduationproject.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByIdentityId(Long identityId);

    @Query(" SELECT a.phoneNumber FROM Customer a WHERE a.identityId= :identityId ")
    String findPhoneNumberByIdentityId(Long identityId);
    
}
