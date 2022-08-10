package com.example.pt08_2072008.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "watchlist", schema = "utssa")
public class WatchlistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idWatchList")
    private int idWatchList;
    @Basic
    @Column(name = "LastWatch")
    private Integer lastWatch;
    @Basic
    @Column(name = "Favorite")
    private Byte favorite;
    @ManyToOne
    @JoinColumn(name = "Movie_idMovie", referencedColumnName = "idMovie", nullable = false)
    private MovieEntity movieByMovieIdMovie;
    @ManyToOne
    @JoinColumn(name = "User_idUser", referencedColumnName = "idUser", nullable = false)
    private UserEntity userByUserIdUser;

    public int getIdWatchList() {
        return idWatchList;
    }

    public void setIdWatchList(int idWatchList) {
        this.idWatchList = idWatchList;
    }

    public Integer getLastWatch() {
        return lastWatch;
    }

    public void setLastWatch(Integer lastWatch) {
        this.lastWatch = lastWatch;
    }

    public Byte getFavorite() {
        return favorite;
    }

    public void setFavorite(Byte favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchlistEntity that = (WatchlistEntity) o;
        return idWatchList == that.idWatchList && Objects.equals(lastWatch, that.lastWatch) && Objects.equals(favorite, that.favorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWatchList, lastWatch, favorite);
    }

    public MovieEntity getMovieByMovieIdMovie() {
        return movieByMovieIdMovie;
    }

    public void setMovieByMovieIdMovie(MovieEntity movieByMovieIdMovie) {
        this.movieByMovieIdMovie = movieByMovieIdMovie;
    }

    public UserEntity getUserByUserIdUser() {
        return userByUserIdUser;
    }

    public void setUserByUserIdUser(UserEntity userByUserIdUser) {
        this.userByUserIdUser = userByUserIdUser;
    }

    public String getDurasiLW(){
        String dur = String.valueOf(lastWatch) + '/' + String.valueOf(movieByMovieIdMovie.getDurasi());
        return dur;
    }

    public String getFavorit(){
        String fav;
        if(favorite == 0){
            fav = "False";
        } else {
            fav = "True";
        }
        return fav;
    }
}
