package azamat.kz.loginapp.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Patterns
import azamat.kz.loginapp.*
import azamat.kz.loginapp.repository.LoginRepositoryImpl


class LoginViewModel: ViewModel() {

    private var liveData = MutableLiveData<Int>()
    private val repository = LoginRepositoryImpl()

    fun validation(email: String, password: String): LiveData<Int> {

        if (email.isEmpty()) {
            liveData.value = EMPTY_EMAIL
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            liveData.value = NOT_VALID_EMAIL
        } else if (password.isEmpty()){
            liveData.value = EMPTY_PASSWORD
        } else if (password.length < 6 || !isValidPassword(password)) {
            liveData.value = NOT_VALID_PASSWORD
        } else {
            liveData.value = SUCCESS
        }
        return liveData
    }

    fun getWeather(): LiveData<ArrayList<Weather>> {
        return repository.getData()
    }

}