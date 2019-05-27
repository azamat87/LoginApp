package azamat.kz.loginapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import azamat.kz.loginapp.Response
import azamat.kz.loginapp.Weather
import azamat.kz.loginapp.remote.RestService
import retrofit2.Call
import retrofit2.Callback

/**
 * Created by Asus on 26.05.2019.
 */
class LoginRepositoryImpl : LoginRepository {

    override fun getData(): LiveData<ArrayList<Weather>> {
        val liveData = MutableLiveData<ArrayList<Weather>>()
        val service = RestService().getService()
        val url = "https://api.openweathermap.org/data/2.5/find?q=Saint Petersburg,RU&appid=dc2aaaabfb8534eabbf93140ccf15f5c"

        service.getData(url).enqueue(object : Callback<Response<ArrayList<Weather>>>{
            override fun onFailure(call: Call<Response<ArrayList<Weather>>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<Response<ArrayList<Weather>>>?, response: retrofit2.Response<Response<ArrayList<Weather>>>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()?.list
                }
            }
        })

        return liveData
    }


}