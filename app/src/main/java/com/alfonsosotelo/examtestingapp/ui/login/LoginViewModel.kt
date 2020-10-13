package com.alfonsosotelo.examtestingapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alfonsosotelo.examtestingapp.repository.LoginRepository
import com.alfonsosotelo.examtestingapp.utils.ApiErrorResponse
import com.alfonsosotelo.examtestingapp.utils.ApiSuccessResponse
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    var repository: LoginRepository
): ViewModel() {

    private val _login: MutableLiveData<Pair<String, String>> = MutableLiveData()

    private val loginResponse = Transformations.switchMap(_login) {
        repository.loginRequest(it.first, it.second)
    }

    val successResponse = Transformations.switchMap(loginResponse) {
        return@switchMap if (it is ApiSuccessResponse) {
            repository.saveUserData(it.body.jwt!!, it.body.id!!)
            MutableLiveData(true)
        } else {
            MutableLiveData(false)
        }
    }

    val errorResponse: LiveData<String?> = Transformations.switchMap(loginResponse) {
        return@switchMap if (it is ApiErrorResponse) {
            MutableLiveData<String?>(it.errorMessage)
        } else {
            MutableLiveData<String?>(null)
        }
    }

    val isLoggedIn = repository.isUserLoggedIn()

    fun login(email: String, password: String) {
        _login.value = Pair(email, password)
    }
}