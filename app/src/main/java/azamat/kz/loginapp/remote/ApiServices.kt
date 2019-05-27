package azamat.kz.loginapp.remote

import azamat.kz.loginapp.Response
import azamat.kz.loginapp.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by Asus on 26.05.2019.
 */
interface ApiServices {

    @GET()
    fun getData(@Url url: String): Call<Response<ArrayList<Weather>>>
}