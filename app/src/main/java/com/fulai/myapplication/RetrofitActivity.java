package com.fulai.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fulai.myapplication.data.MessageModel;
import com.fulai.myapplication.postintface.JokeService;
import com.fulai.myapplication.postintface.RxJavaOPer;
import com.fulai.myapplication.postintface.ServiceManager;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        int start = 0, end = 0;
        getMovie(start, end);

    }

    private void getMovie(int start, int end) {
        //ServiceManager.getInstance().getiMovieTop250().getMovie(start, end);

        RxJavaOPer.readData().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void getMessage(){
        JokeService jokeService = ServiceManager.getInstance().create(JokeService.class);
        Call<MessageModel> message = jokeService.getMessage("1");
        message.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful()){
                    MessageModel body = response.body();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

        try {
            //同步
            Response<MessageModel> execute = message.execute();
            MessageModel body = execute.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}
