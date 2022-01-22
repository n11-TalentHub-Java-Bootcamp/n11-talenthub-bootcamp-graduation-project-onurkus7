package com.onurkus.graduationproject.service.entityservice;

import com.onurkus.graduationproject.dao.UserDao;
import com.onurkus.graduationproject.entity.User;
import com.onurkus.graduationproject.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService extends BaseEntityService<User,UserDao> {

    public UserEntityService(UserDao dao) {
        super(dao);
    }
}
