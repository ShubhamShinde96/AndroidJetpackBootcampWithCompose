package com.shubham.viewmodelscopewithcoroutines

import androidx.lifecycle.*
import com.shubham.viewmodelscopewithcoroutines.model.User
import com.shubham.viewmodelscopewithcoroutines.model.UserRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainActivityViewModel : ViewModel() {

    private var usersRepository = UserRepository()

    // Android architecture components team introduced a new coroutine building block for livedata
    // This new block will automatically execute when LiveData becomes active.
    // It automatically decides when to stop executing and cancel the coroutine inside the building block
    // considering the state of the lifecycle owner.
    // Inside the LiveData building block, you can use emit() function to set a value to the LiveData.

    var users = liveData {
        val result = usersRepository.getUsers()
        emit(result)
    }

    // Now compare above code with below commented method and see how many less lines we have.

    /*fun getUserData() {

        viewModelScope.launch {

            var result : List<User> = listOf()

            withContext(IO){

                result = usersRepository.getUsers()

            }

            if(!result.isEmpty()){
                users.value = result
            }
        }
    }*/

}