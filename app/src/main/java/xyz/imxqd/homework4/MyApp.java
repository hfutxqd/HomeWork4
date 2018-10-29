package xyz.imxqd.homework4;

import android.app.Application;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.imxqd.homework4.net.base.HomeWorkService;
import xyz.imxqd.homework4.net.base.NetworkUtil;

public class MyApp extends Application {
    private static final String TAG = "MyApp";

    private static MyApp app;

    private Retrofit retrofit;
    private OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        client = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(new Cache(getCacheDir(), 50 * 1024 * 1024)) // 50MB
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        if(!NetworkUtil.isNetworkAvailable(getApplicationContext())){
                            request = request.newBuilder()
                                    .cacheControl(CacheControl.FORCE_CACHE)
                                    .build();
                            Log.w(TAG, "no network, use cached data!");
                        }
                        Log.d(TAG, request.toString());
                        Response response = chain.proceed(request);
                        Log.d(TAG, response.toString());
                        return response;
                    }
                })
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(HomeWorkService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        app = this;
    }

    public static MyApp get() {
        return app;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
