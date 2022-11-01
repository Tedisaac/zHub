package com.ndei.zhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ndei.zhub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private val mainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        supportActionBar?.hide()

        setUpRecyclerView()

        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        movieViewModel.getPopularMovies()
        movieViewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            movieAdapter.setMoviesList(movieList as ArrayList<Result>)
        })




    }

    private fun setUpRecyclerView() {
        movieAdapter = MovieAdapter()
        val staggeredGridLayoutManager = GridLayoutManager(this,2, LinearLayoutManager.VERTICAL, false)
        mainBinding.moviesRecycler.apply {
           layoutManager = staggeredGridLayoutManager
           adapter = movieAdapter
       }
    }
}