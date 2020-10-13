package com.alfonsosotelo.examtestingapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alfonsosotelo.examtestingapp.repository.LoginRepository
import com.alfonsosotelo.examtestingapp.repository.remote.objects.RegisterUser
import com.alfonsosotelo.examtestingapp.utils.ApiErrorResponse
import com.alfonsosotelo.examtestingapp.utils.ApiSuccessResponse
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    var repository: LoginRepository
): ViewModel() {

    private val _register: MutableLiveData<RegisterUser> = MutableLiveData()

    private val registerResponse = Transformations.switchMap(_register) {
        repository.registerRequest(it)
    }

    val successResponse = Transformations.switchMap(registerResponse) {
        return@switchMap if (it is ApiSuccessResponse) {
            repository.saveUserData(it.body.jwt!!, it.body.id!!)
            MutableLiveData(true)
        } else {
            MutableLiveData(false)
        }
    }

    val errorResponse: LiveData<String?> = Transformations.switchMap(registerResponse) {
        return@switchMap if (it is ApiErrorResponse) {
            MutableLiveData<String?>(it.errorMessage)
        } else {
            MutableLiveData<String?>(null)
        }
    }


    fun register(username: String, password: String, email: String) {
        _register.value = RegisterUser().apply {
            this.username = username
            this.password = password
            this.email = email
        }
    }
}