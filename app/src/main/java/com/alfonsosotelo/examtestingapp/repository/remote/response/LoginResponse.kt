package com.alfonsosotelo.examtestingapp.repository.remote.response

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("jwt")
    var jwt: String? = null

}