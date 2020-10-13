package com.alfonsosotelo.examtestingapp.repository

import androidx.lifecycle.LiveData
import com.alfonsosotelo.examtestingapp.repository.remote.objects.User
import com.alfonsosotelo.examtestingapp.repository.remote.response.UsersResponse
import com.alfonsosotelo.examtestingapp.repository.remote.utils.Webservice
import com.alfonsosotelo.examtestingapp.utils.ApiResponse
import javax.inject.Inject

class UserRepository @Inject constructor(var webservice: Webservice) {

    fun getUsers(): LiveData<ApiResponse<UsersResponse>> {
        return webservice.getUsers()
    }

    fun getUser(userId: String): LiveData<ApiResponse<User>> {
        return webservice.getUser(userId)
    }
}