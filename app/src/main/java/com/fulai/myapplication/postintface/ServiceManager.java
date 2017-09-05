package com.fulai.myapplication.postintface;

import com.fulai.myapplication.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dengmao on 17/8/24.
 */

public class ServiceManager {
    private static ServiceManager instance = null;

    public synchronized static ServiceManager getInstance() {
        return instance != null ? instance : new ServiceManager();
    }

    private OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(BuildConfig.DEBUG ?
                            HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
            .build();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(StrUtil.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    private IMovieTop250 iMovieTop250 = retrofit.create(IMovieTop250.class);

    public IMovieTop250 getiMovieTop250() {
        return iMovieTop250;
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
