package com.alfonsosotelo.examtestingapp.repository.remote.utils

class NetworkConstants {
    companion object {
        const val SERVER_BASE_URL = "http://api-candidates-dev.us-west-2.elasticbeanstalk.com"

        const val LOGIN_METHOD = "/login"
        const val REGISTER_METHOD = "/users"
        const val GET_USERS_METHOD = "/users"
        const val GET_USER_METHOD = "/users/{id}"
    }
}