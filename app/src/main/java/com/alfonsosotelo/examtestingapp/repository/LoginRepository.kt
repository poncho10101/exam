package com.alfonsosotelo.examtestingapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alfonsosotelo.examtestingapp.repository.local.PreferencesHelper
import com.alfonsosotelo.examtestingapp.repository.remote.objects.RegisterUser
import com.alfonsosotelo.examtestingapp.repository.remote.request.LoginRequest
import com.alfonsosotelo.examtestingapp.repository.remote.response.LoginResponse
import com.alfonsosotelo.examtestingapp.repository.remote.utils.Webservice
import com.alfonsosotelo.examtestingapp.utils.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(var webservice: Webservice, var preferencesHelper: PreferencesHelper) {

    fun loginRequest(email: String, password: String): LiveData<ApiResponse<LoginResponse>> {
        return webservice.login(LoginRequest().apply {
            this.email = email
            this.password = password
        })
    }

    fun registerRequest(user: RegisterUser): LiveData<ApiResponse<LoginResponse>> {
        return webservice.register(user)
    }

    fun isUserLoggedIn(): LiveData<Boolean> {
        return MutableLiveData(preferencesHelper.isLoggedIn)
    }


    fun saveUserData(jwt: String, id: String) {
        preferencesHelper.userJWT = jwt
        preferencesHelper.userId = id
        preferencesHelper.isLoggedIn = true
    }
}