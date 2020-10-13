package com.alfonsosotelo.examtestingapp.repository.remote.objects

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("id")
    var id: String = ""

    @SerializedName("email")
    var email: String = ""

    @SerializedName("password")
    var password: String = ""

    @SerializedName("username")
    var username: String = ""
}