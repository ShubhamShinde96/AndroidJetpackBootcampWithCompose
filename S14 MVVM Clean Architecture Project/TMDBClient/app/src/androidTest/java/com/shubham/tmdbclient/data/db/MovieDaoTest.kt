package com.shubham.tmdbclient.data.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.shubham.tmdbclient.data.model.movie.Movie
import com.shubham.tmdbclient.db.MovieDao
import com.shubham.tmdbclient.db.TMDBDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat


// we need to annotate this class to run with AndroidJunit4
@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    // since we're using architecture components, To execute those tasks synchronously, we need to use instantTaskExecutorRule
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // defining reference variable for movie dao
    private lateinit var dao: MovieDao // MovieDao is the subject under test in this class.

    private lateinit var tmdbDatabase: TMDBDatabase

    // Room library has a special database builder named inMemoryDatabaseBuilder, this inMemoryDatabaseBuilder
    // allows us to create the temporary database for testing, the database will be created in system memory,
    // when we kill the process after testing the app, database will be removed and data will not be persisted.

    @Before
    fun setUp() {
        tmdbDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java
        ).build()

        dao = tmdbDatabase.movieDao()
    }

    // also we need to close the database after the testing is done. For that we're implementing teardown
    // function annotated with @After annotation.
    @After
    fun tearDown() {
        // to implement, alt+insert > teardown function.
        tmdbDatabase.close()
    }

    // Now we'll write the test case to test this saveMovies function.

    // We'll create a list of movie object, save them to the database, and then get the saved list from the
    // database and compare that with the original list.
    // We'll be using kotlin coroutines runBlocking builder here
    @Test
    fun saveMoviesTest() = runBlocking {

        val movies = listOf(
            Movie(1, "overview1", "posterPath1", "date1", "title1"),
            Movie(2, "overview2", "posterPath2", "date2", "title2"),
            Movie(3, "overview3", "posterPath3", "date3", "title3")
        )

        dao.saveMovies(movies)

        // here we've to assume that getMovies function working correctly, it's a basic get query. if it's not
        // working correctly we'll get the errors.
        val allMovies = dao.getAllMovies()

        // then let's use assertThat function to compare the received list from the database with the original list.
        assertThat(allMovies).isEqualTo(movies)
    }


    @Test
    fun deleteMoviesTest() = runBlocking {

        val movies = listOf(
            Movie(1, "overview1", "posterPath1", "date1", "title1"),
            Movie(2, "overview2", "posterPath2", "date2", "title2"),
            Movie(3, "overview3", "posterPath3", "date3", "title3")
        )

        dao.saveMovies(movies)

        // in order to delete movies we first need to have some movies into db that's why we have inserted above movies.

        dao.deleteAllMovies()

        val moviesResult = dao.getAllMovies()

        Truth.assertThat(moviesResult).isEmpty()

    }
}