package com.shubham.viewmodelscopewithcoroutines.model

import kotlinx.coroutines.delay

class UserRepository {

    suspend fun getUsers() : List<User> {

        delay(8000)

        // here we are mimicking the delay, like we are getting this data from server/API

        val users: List<User> = listOf(
            User(1, "Shubham"),
            User(2, "Kshitij"),
            User(3, "Sujay"),
            User(4, "Ajinkya"),
            User(5, "Sumit"),
        )

        return users
    }

}