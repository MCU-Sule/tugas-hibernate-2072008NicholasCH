package com.example.pt08_2072008.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "utssa")
public class UserEntity {

    @Override
    public String toString() {
        return userName;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUser")
    private int idUser;
    @Basic
    @Column(name = "UserName")
    private String userName;
    @Basic
    @Column(name = "UserPassword")
    private String userPassword;
    @OneToMany(mappedBy = "userByUserIdUser")
    private Collection<WatchlistEntity> watchlistsByIdUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return idUser == that.idUser && Objects.equals(userName, that.userName) && Objects.equals(userPassword, that.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, userName, userPassword);
    }

    public Collection<WatchlistEntity> getWatchlistsByIdUser() {
        return watchlistsByIdUser;
    }

    public void setWatchlistsByIdUser(Collection<WatchlistEntity> watchlistsByIdUser) {
        this.watchlistsByIdUser = watchlistsByIdUser;
    }
}
