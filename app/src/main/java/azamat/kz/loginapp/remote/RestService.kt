package azamat.kz.loginapp.remote

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Asus on 26.05.2019.
 */
class RestService {

    val MAX_CONNECTION_TIMEOUT = 50L
    val MAX_READ_TIMEOUT = 50L
    val MAX_WRITE_TIMEOUT = 50L

    fun getService(): ApiServices {
        return makeService(okHttp())
    }

    private fun okHttp(): OkHttpClient{
        return OkHttpClient().newBuilder()
                .connectTimeout(MAX_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(MAX_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(MAX_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    private fun makeService(okHttpClient: OkHttpClient): ApiServices {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
        return retrofit.create(ApiServices::class.java)
    }

}