package com.onurkus.graduationproject.customer.service.entityservice;

import com.onurkus.graduationproject.customer.dao.CustomerDao;
import com.onurkus.graduationproject.customer.entity.Customer;
import com.onurkus.graduationproject.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class CustomerEntityService extends BaseEntityService<Customer, CustomerDao> {

    public CustomerEntityService(CustomerDao dao) {
        super(dao);
    }

    public Customer findByIdentityCustomer(Long identityId){
        return getDao().findByIdentityId(identityId);
    }


}
