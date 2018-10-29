package xyz.imxqd.homework4.net.base;


import io.reactivex.Observable;
import retrofit2.http.GET;
import xyz.imxqd.homework4.net.model.HomePage;
import xyz.imxqd.homework4.net.model.HttpResult;

public interface HomeWorkService {

    String BASE_URL = "http://192.168.16.38:8000/homework/lenny/";

    @GET("index")
    Observable<HttpResult<HomePage>> homePage();
}
