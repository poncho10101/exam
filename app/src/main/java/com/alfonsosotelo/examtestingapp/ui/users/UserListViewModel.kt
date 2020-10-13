package com.alfonsosotelo.examtestingapp.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alfonsosotelo.examtestingapp.repository.UserRepository
import com.alfonsosotelo.examtestingapp.repository.remote.objects.User
import com.alfonsosotelo.examtestingapp.utils.ApiSuccessResponse
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    var userRepository: UserRepository
): ViewModel() {

    private val _users = userRepository.getUsers()
    val users = Transformations.switchMap(_users) {
        return@switchMap if (it is ApiSuccessResponse) {
            MutableLiveData(it.body.resources)
        } else {
            MutableLiveData<List<User>>(listOf())
        }
    }
}