package com.ndei.zhub

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private var moviesLiveData = MutableLiveData<List<Result>>()

    fun getPopularMovies(){
        RetrofitInstance.api.getPopularMovies(API_KEY).enqueue(object : Callback<MoviesData>{
            override fun onResponse(call: Call<MoviesData>, response: Response<MoviesData>) {
                if (response.isSuccessful && response.body() != null){
                    moviesLiveData.value = response.body()!!.results
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MoviesData>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}", )
            }

        })
    }

    fun observeMovieLiveData() : LiveData<List<Result>>{
        return moviesLiveData
    }
}