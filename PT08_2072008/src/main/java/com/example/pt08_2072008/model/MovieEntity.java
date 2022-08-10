package com.example.pt08_2072008.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "movie", schema = "utssa")
public class MovieEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idMovie")
    private int idMovie;
    @Basic
    @Column(name = "Title")
    private String title;
    @Basic
    @Column(name = "Genre")
    private String genre;
    @Basic
    @Column(name = "Durasi")
    private Integer durasi;
    @OneToMany(mappedBy = "movieByMovieIdMovie")
    private Collection<WatchlistEntity> watchlistsByIdMovie;

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDurasi() {
        return durasi;
    }

    public void setDurasi(Integer durasi) {
        this.durasi = durasi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return idMovie == that.idMovie && Objects.equals(title, that.title) && Objects.equals(genre, that.genre) && Objects.equals(durasi, that.durasi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovie, title, genre, durasi);
    }

    public Collection<WatchlistEntity> getWatchlistsByIdMovie() {
        return watchlistsByIdMovie;
    }

    public void setWatchlistsByIdMovie(Collection<WatchlistEntity> watchlistsByIdMovie) {
        this.watchlistsByIdMovie = watchlistsByIdMovie;
    }

    @Override
    public String toString() {
        return title;
    }
}
