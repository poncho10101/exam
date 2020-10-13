package com.alfonsosotelo.examtestingapp.repository.remote.utils

import androidx.lifecycle.LiveData
import com.alfonsosotelo.examtestingapp.repository.remote.objects.RegisterUser
import com.alfonsosotelo.examtestingapp.repository.remote.objects.User
import com.alfonsosotelo.examtestingapp.repository.remote.request.LoginRequest
import com.alfonsosotelo.examtestingapp.repository.remote.response.LoginResponse
import com.alfonsosotelo.examtestingapp.repository.remote.response.UsersResponse
import com.alfonsosotelo.examtestingapp.utils.ApiResponse
import retrofit2.http.*

interface Webservice {

    @POST(NetworkConstants.LOGIN_METHOD)
    fun login(@Body body: LoginRequest): LiveData<ApiResponse<LoginResponse>>

    @POST(NetworkConstants.REGISTER_METHOD)
    fun register(@Body body: RegisterUser): LiveData<ApiResponse<LoginResponse>>

    @GET(NetworkConstants.GET_USERS_METHOD)
    fun getUsers(@Query("limit") limit: String = "100"): LiveData<ApiResponse<UsersResponse>>

    @GET(NetworkConstants.GET_USER_METHOD)
    fun getUser(@Path(value = "id", encoded = true) userId: String): LiveData<ApiResponse<User>>
}