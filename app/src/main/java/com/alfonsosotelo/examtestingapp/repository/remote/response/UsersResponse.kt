package com.alfonsosotelo.examtestingapp.repository.remote.response

import com.alfonsosotelo.examtestingapp.repository.remote.objects.User

class UsersResponse {
    var resources: List<User> = listOf()
    var count: String = "0"
}