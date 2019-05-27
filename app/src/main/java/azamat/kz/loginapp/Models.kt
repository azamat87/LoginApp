package azamat.kz.loginapp

/**
 * Created by Asus on 26.05.2019.
 */
data class Weather(val id: Int,
                   val name: String,
                   val main: Main,
                   val sys: Sys
)

data class Main(val temp: Double,
                val pressure: Double,
                val humidity: Double,
                val temp_min: Double,
                val temp_max: Double)

data class Sys(val country: String)

data class Response<T>(val predictions: T,
                       val list: T,
                       val status: String)
