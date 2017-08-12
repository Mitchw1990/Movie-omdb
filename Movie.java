package com.movies.movieapp.movieMVC;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="movies")
public final class Movie {

    @Id
    private String imdbID;

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;

    @NotNull
    private String title;

    @NotNull
    private String year;

    @NotNull
    private String poster;

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
