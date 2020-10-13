package com.alfonsosotelo.examtestingapp.ui.users.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alfonsosotelo.examtestingapp.repository.UserRepository
import com.alfonsosotelo.examtestingapp.repository.remote.objects.User
import com.alfonsosotelo.examtestingapp.utils.ApiResponse
import com.alfonsosotelo.examtestingapp.utils.ApiSuccessResponse
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(
    var repository: UserRepository
): ViewModel(){

    private val _userId = MutableLiveData<String?>(null)

    private val _user = Transformations.switchMap(_userId) {
        return@switchMap if (it != null)
            repository.getUser(it)
        else
            MutableLiveData<ApiResponse<User>>(null)
    }

    val user = Transformations.switchMap(_user) {
        return@switchMap if (it is ApiSuccessResponse) {
            MutableLiveData(it.body)
        } else {
            MutableLiveData<User?>(null)
        }
    }

    fun getUser(id: String) {
        _userId.value = id
    }
}