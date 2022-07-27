package com.shubham.viewmodelscopewithcoroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.viewmodelscopewithcoroutines.model.User
import com.shubham.viewmodelscopewithcoroutines.model.UserRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class MainActivityViewModel : ViewModel() {

    /*private val myJob = Job()
    private val myScope = CoroutineScope(IO)*/

    private var usersRepository = UserRepository()
    var users: MutableLiveData<List<User>> = MutableLiveData()

    fun getUserData() {

//        myScope.launch {
        viewModelScope.launch {
            // here we are simply using extension property "viewModelScope" instead of "myScope" created by us.
            // Since we are using viewModelScope now we don't need to use onCleared() method.
            // There kind of coroutines are useful here when you have work that needs to be done only if the ViewModel is active.

            var result : List<User> = listOf()
            // Now we're going to get the list of users by invoking the getUsers() functions of the userRepository, it's a
            // long running task so we should switch the thread of the coroutine using "withContext" to a background thread

            withContext(IO){

                result = usersRepository.getUsers()

            }

            if(!result.isEmpty()){
                users.value = result
            }


        }
    }

    /*override fun onCleared() {
        super.onCleared()
        myJob.cancel()
        // but if you have like 20 ViewModel classes in your app, then doing this cancellation manually might be
        // time consuming, so to avoid those unnecessary boilerplate code we can use viewModelScope, this new
        // viewModelScope is bounded to ViewModels lifecycle, it has created to automatically handle cancellation
        // when the view models onCleared() is called.
        // We can easily use this scope from an extension function available on ViewModel KTX library.
        // So we need to add that dependency in our app level gradle file.
    }*/

}