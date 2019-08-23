package com.example.fourtitudetask1.task3.mvp.movie_detail;

import android.util.Log;

import com.example.fourtitudetask1.task3.api.OmdbHttpClient;
import com.example.fourtitudetask1.task3.api.RetrofitUtil;
import com.example.fourtitudetask1.task3.model.MovieApiResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsModel implements MovieDetailsContract.Model {

    private final String TAG = "MovieDetailsModel";
    private MovieApiResponse movie;

    @Override
    public void getMovieDetails(OnFinishedListener onFinishedListener, String imdbID) {
        OmdbHttpClient apiService =
                RetrofitUtil.getRetrofitInstance().create(OmdbHttpClient.class);

        apiService.getMovieById(OmdbHttpClient.OMDB_API_KEY, imdbID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieApiResponse movieApiResponse) {
                         movie = movieApiResponse;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                        onFinishedListener.onFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        onFinishedListener.onSuccess(movie);
                    }
                });


//        Call<MovieApiResponse> call = apiService.getMovieById(OmdbHttpClient.OMDB_API_KEY, imdbID);
//        call.enqueue(new Callback<MovieApiResponse>() {
//            @Override
//            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
//
//                MovieApiResponse movieApiResponse = response.body();
//                Log.d(TAG, "Number of movies received: " + movieApiResponse);
//
//                onFinishedListener.onSuccess(movieApiResponse);
//            }
//
//            @Override
//            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
//                Log.e(TAG, t.toString());
//                onFinishedListener.onFailure(t);
//            }
//        });
    }
}