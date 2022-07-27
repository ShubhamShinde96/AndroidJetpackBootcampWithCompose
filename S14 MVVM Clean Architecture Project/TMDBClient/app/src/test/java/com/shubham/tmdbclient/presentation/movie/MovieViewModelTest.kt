package com.shubham.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.shubham.tmdbclient.data.model.movie.Movie
import com.shubham.tmdbclient.data.repository.movie.FakeMovieRepository
import com.shubham.tmdbclient.domain.use_cases.GetMoviesUseCase
import com.shubham.tmdbclient.domain.use_cases.UpdateMoviesUseCase
import com.shubham.tmdbclient.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// We're going to run simulated android code in our test source set.
// For that we need to use roboelectric dependency and the AndroidJunit4 test runner.
// So we need to add it with "RunWith" annotation.
@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {

    // generated this test class by going into MovieViewModel then alt+insert > Test > select Junit4 > write name
    // notice this test class being created in same package structure, as android created for the same way
    // as the MovieViewModel is inside package structure, its a best and recommended practice.


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    // The instantTaskExecutorRule is the class from JUnit library. This rule runs all jetpack architecture
    // component related background jobs in the same thread & because of that test result will happen synchronously
    // and in repeatable order.
    // When we write tests that include testing LiveData, we should use this instantTaskExecutorRule

    // creating object reference variable for MovieViewModel
    private lateinit var movieViewModel: MovieViewModel // <- is the subject under test of this test class.

    @Before
    fun setUp() {


        // Now MovieViewModel constructor has 2 dependencies: 1] getMoviesUseCase, 2] updateMoviesUseCase
        // getMoviesUseCase & updateMoviesUseCase has MovieRepository as a dependency
        // So first of all we need to create MovieRepository instance.
        // During a previous lesson we created a mock for testing, now during this lesson we will learn how to
        // use a Fake for testing, we're going to create a fake MovieRepository class.
        // The MovieRepositoryImpl class implements MovieRepository interface, so now we're going to create a Fake
        // MovieRepository implementing the same interface.
        // So now creating the new package following the same package structure as MovieRepositoryImpl

        // com.shubham.tmdbclient.data.repository.movie

        val fakeMovieRepository = FakeMovieRepository()
        // then create instance of getMoviesUseCase & updateMoviesUseCase using that fake MovieRepository instance.
        val getMovieUSeCase = GetMoviesUseCase(fakeMovieRepository)
        val updateMovieUSeCase = UpdateMoviesUseCase(fakeMovieRepository)

        movieViewModel = MovieViewModel(getMovieUSeCase, updateMovieUSeCase)

        // now lets generate test cases for two functions.

    }


    @Test
    fun getMovies_returnsExistingMovieList() {

        // here we'll create a list of movies same as the current list in Fake repository and compare it with
        // the list returned from the repository
        val movies = mutableListOf<Movie>()
        movies.add(Movie(1, "overview1", "posterPath1", "date1", "title1"))
        movies.add(Movie(2, "overview2", "posterPath2", "date2", "title2"))

//        val currentMoviesList = movieViewModel.getMovies()
        // here we have a problem, as this movieViewModel returns the list as a liveData & to compare we need to
        // convert it into a normal list of movies, there are different ways to do it but the easiest way is using
        // livedata EXTENSION function called LiveDataTestUtil, LiveDataTestUtil is a reusable code sample you can
        // find from google documentations, so creating the class LiveDataTestUtil

        val currentMoviesList = movieViewModel.getMovies().getOrAwaitValue() // we're using this getOrAwaitValue() fun
        // to convert our movies list from live data format to normal data format

        assertThat(currentMoviesList).isEqualTo(movies) // remove junit assert and import truth assert
    }


    @Test
    fun updateMovies_returnsUpdatedMovieList() {


        val movies = mutableListOf<Movie>()
        movies.add(Movie(3, "overview3", "posterPath3", "date3", "title3"))
        movies.add(Movie(4, "overview4", "posterPath4", "date4", "title4"))

        val updatedMoviesList = movieViewModel.updateMovieList().getOrAwaitValue()

        assertThat(updatedMoviesList).isEqualTo(movies)
    }


}