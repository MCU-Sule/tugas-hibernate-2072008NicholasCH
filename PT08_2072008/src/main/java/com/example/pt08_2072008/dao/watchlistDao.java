package com.example.pt08_2072008.dao;

import com.example.pt08_2072008.model.UserEntity;
import com.example.pt08_2072008.model.WatchlistEntity;
import com.example.pt08_2072008.util.utilConnection;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class watchlistDao implements interfaceDao<WatchlistEntity> {
    Session session;
    @Override
    public List<WatchlistEntity> getData() {
        List<WatchlistEntity> wList;
        session = utilConnection.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(WatchlistEntity.class);
        query.from(WatchlistEntity.class);
        wList = session.createQuery(query).getResultList();
        session.close();
        return wList;
    }

    @Override
    public int addData(WatchlistEntity data) {
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
    public int upData(WatchlistEntity data) {
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
    public int delData(WatchlistEntity data) {
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

    public List<WatchlistEntity> filterData(UserEntity data){
        List<WatchlistEntity>wList;
        session = utilConnection.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(WatchlistEntity.class);
        Root<WatchlistEntity> root = query.from(WatchlistEntity.class);
        Predicate p1 = builder.equal(root.get("userByUserIdUser"), data);
        query.where(p1);
        wList = session.createQuery(query).getResultList();
        session.close();
        return wList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
