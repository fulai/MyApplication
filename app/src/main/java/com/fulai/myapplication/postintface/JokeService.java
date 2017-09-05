package com.fulai.myapplication.postintface;

import com.fulai.myapplication.data.MessageModel;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Dengmao on 17/8/28.
 */

public interface JokeService {
    @Headers({
            "User-Agent:android",
            "apikey:123123123"
    })
    @POST
    Call<MessageModel> post(@Url String url, @QueryMap Map<String, String> map);

    @GET("mobile/active")
    Call<MessageModel> get(@Header("token") String token, @Query("id") int activeId);

    @GET("/joke/content/list.from")
    Call<MessageModel> getMessage(@Query("sort") String sort);

    @GET("/joke/book/{index}")
    Observable<MessageModel> getObserveMsg(@Path("index") int index, @Query("size") int size);

    /**
     * 很多情况下，我们需要上传json格式的数据。比如当我们注册新用户的时候，因为用户注册时的数据相对较多，
     * 并可能以后会变化，这时候，服务端可能要求我们上传json格式的数据。此时就要@Body注解来实现。
     * 直接传入实体,它会自行转化成Json
     *
     * @param url
     * @param messageModel
     * @return
     */
    @POST("api/{url}/newsList")
    Call<MessageModel> login(@Path("url") String url, @Body MessageModel messageModel);

    /**
     * 单张图片上传
     * retrofit 2.0的上传和以前略有不同，需要借助@Multipart注解、@Part和MultipartBody实现。
     *
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @POST("{url}")
    Call<MessageModel> upload(@Path("url") String url, @Part MultipartBody.Part file);

    @Multipart
    @POST
    Call<MessageModel> uploadMult(@PartMap Map<String, MultipartBody.Part> map);

    /**
     * 文件下载
     *
     * @param url
     * @return
     */
    @Streaming
    @GET
    Call<MessageModel> downloadPic(@Url String url);
}