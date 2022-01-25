package com.onurkus.graduationproject.credit.service.entityservice;

import com.onurkus.graduationproject.credit.dao.CreditDao;
import com.onurkus.graduationproject.credit.entity.Credit;
import com.onurkus.graduationproject.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class CreditEntityService extends BaseEntityService<Credit, CreditDao> {


    public CreditEntityService(CreditDao dao) {
        super(dao);
    }

    public Credit findCreditLimitByIdentityId(Long identityId) {
        return getDao().findByIdentityId(identityId);
    }

}
