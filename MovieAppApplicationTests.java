package com.movies.movieapp;

import com.movies.movieapp.movieMVC.MovieController;
import com.movies.movieapp.movieMVC.MovieRepository;
import com.movies.movieapp.movieMVC.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import java.util.Arrays;
import java.util.Collections;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieAppApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void createReturns201IfSaveSuccessful() throws Exception {

        mockMvc.perform(
                post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().is(201));

        verify(movieRepository).save(any(Movie.class));
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void returnsListOfMoviesIfRepositoryNotEmpty() throws Exception {
        Movie anyMovie = new Movie();
        anyMovie.setTitle("Batman");
        anyMovie.setPoster("google.com");
        anyMovie.setYear("2003");


        when(movieRepository.findAll()).thenReturn(Arrays.asList(anyMovie));

        mockMvc.perform(
                get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Batman")));

        verify(movieRepository).findAll();
        verifyNoMoreInteractions(movieRepository);

    }

    @Test
    public void returnsEmptyListOfMoviesIfRepositoryEmpty() throws Exception {

        when(movieRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(
                get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(0)));

        verify(movieRepository).findAll();
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void deleteMovieChecksIfMovieIsInRepo () throws Exception {

        doNothing().when(movieRepository).delete(any(Integer.class));

        mockMvc.perform(
                delete("/0"));

        verify(movieRepository).findOne(0);
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void deleteMovieReturnsAcceptedStatusIfSuccessful () throws Exception {
        when(movieRepository.findOne(0)).thenReturn(new Movie ());

        mockMvc.perform(
                delete("/0"))
                .andExpect(status().isAccepted());

        verify(movieRepository).findOne(0);
        verify(movieRepository).delete(0);
        verifyNoMoreInteractions(movieRepository);

    }

}
