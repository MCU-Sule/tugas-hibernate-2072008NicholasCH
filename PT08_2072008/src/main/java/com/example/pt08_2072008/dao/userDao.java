package com.example.pt08_2072008.dao;

import com.example.pt08_2072008.model.MovieEntity;
import com.example.pt08_2072008.model.UserEntity;
import com.example.pt08_2072008.util.utilConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class userDao implements interfaceDao<UserEntity>{
    Session session;
    Transaction transaction;
    @Override
    public List<UserEntity> getData() {
        session = utilConnection.getSession();
        List<UserEntity> uList;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(UserEntity.class);
        query.from(UserEntity.class);
        uList = session.createQuery(query).getResultList();
        session.close();
        return uList;
    }

    @Override
    public int addData(UserEntity data) {
        int hasil;
        session = utilConnection.getSession();
        transaction = session.beginTransaction();
        try{
            session.save(data);
            transaction.commit();
            hasil = 1;
        }
        catch (Exception e){
            hasil = 0;
            transaction.rollback();
        }

        session.close();
        return hasil;
    }

    @Override
    public int upData(UserEntity data) {
        int hasil;
        session = utilConnection.getSession();
        transaction = session.beginTransaction();
        try{
            session.update(data);
            transaction.commit();
            hasil = 1;
        }
        catch (Exception e){
            hasil = 0;
            transaction.rollback();
        }

        session.close();
        return hasil;
    }

    @Override
    public int delData(UserEntity data) {
        int hasil;
        session = utilConnection.getSession();
        transaction = session.beginTransaction();
        try{
            session.delete(data);
            transaction.commit();
            hasil = 1;
        }
        catch (Exception e){
            transaction.rollback();
            hasil = 0;
        }

        session.close();
        return hasil;
    }
}
