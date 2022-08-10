package com.example.pt08_2072008.dao;

import com.example.pt08_2072008.model.MovieEntity;
import com.example.pt08_2072008.util.utilConnection;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class movieDao implements interfaceDao<MovieEntity> {
    Session session;
    @Override
    public List<MovieEntity> getData() {
        List<MovieEntity> mList;
        session = utilConnection.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(MovieEntity.class);
        query.from(MovieEntity.class);
        mList = session.createQuery(query).getResultList();
        session.close();
        return mList;
    }

    @Override
    public int addData(MovieEntity data) {
        int hasil;
        session = utilConnection.getSession();
        try{
            session.save(data);
            hasil = 1;
        }
        catch (Exception e){
            hasil = 0;
        }

        session.close();
        return hasil;
    }

    @Override
    public int upData(MovieEntity data) {
        int hasil;
        session = utilConnection.getSession();
        try{
            session.update(data);
            hasil = 1;
        }
        catch (Exception e){
            hasil = 0;
        }

        session.close();
        return hasil;
    }

    @Override
    public int delData(MovieEntity data) {
        int hasil;
        session = utilConnection.getSession();
        try{
            session.delete(data);
            hasil = 1;
        }
        catch (Exception e){
            hasil = 0;
        }

        session.close();
        return hasil;
    }

    public List<MovieEntity> filterData(String data) {
        session = utilConnection.getSession();
        List<MovieEntity> mList;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(MovieEntity.class);
        Root<MovieEntity> root = query.from(MovieEntity.class);
        Predicate p1 = builder.like(root.get("genre"), "%" + data + "%");
        query.where(p1);
        mList = session.createQuery(query).getResultList();
        session.close();
        return mList;
    }
}
