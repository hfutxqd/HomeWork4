package xyz.imxqd.homework4.net.base;

import io.reactivex.Observable;
import retrofit2.http.GET;
import xyz.imxqd.homework4.net.model.HomePage;

public interface HomeWorkService {

    @GET("http://192.168.16.38:8000/homework/lenny/index")
    Observable<HomePage> homePage();
}
