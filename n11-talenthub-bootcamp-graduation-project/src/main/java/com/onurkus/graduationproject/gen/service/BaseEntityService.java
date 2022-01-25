package com.onurkus.graduationproject.gen.service;

import com.onurkus.graduationproject.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public abstract class BaseEntityService <E extends BaseEntity,D extends JpaRepository>{

    private D dao;

    public List<E> findAll(){
        return dao.findAll();
    }

    public E save(E e){
        return (E) dao.save(e);
    }

    public void delete(E e){
        dao.delete(e);
    }

    public D getDao() {
        return dao;
    }
}
