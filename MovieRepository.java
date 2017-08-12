package com.movies.movieapp.movieMVC;


import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {


}