package com.example.mybooking;
        import java.util.concurrent.TimeUnit;

        import okhttp3.OkHttpClient;
        import okhttp3.logging.HttpLoggingInterceptor;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

class ApiClient {
    public static final String BASE_URL = "http://www.tourbooking.riolabz.com/";//BASE_URL is created variable.

    private static Retrofit retrofit = null;

    public static Retrofit getApiService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();//httplogginginterceptor log informationsnu vendi.debug modilu helpnu
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(180, TimeUnit.SECONDS)//import time unit
                .writeTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();



        return retrofit;
    }

}
