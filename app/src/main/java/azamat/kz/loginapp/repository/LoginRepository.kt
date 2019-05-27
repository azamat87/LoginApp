package azamat.kz.loginapp.repository

import android.arch.lifecycle.LiveData
import azamat.kz.loginapp.Weather

/**
 * Created by Asus on 26.05.2019.
 */
interface LoginRepository {

    fun getData(): LiveData<ArrayList<Weather>>

}