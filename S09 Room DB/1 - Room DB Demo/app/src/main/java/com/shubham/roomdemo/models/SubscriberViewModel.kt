package com.shubham.roomdemo.models

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.roomdemo.Event
import com.shubham.roomdemo.db.Subscriber
import com.shubham.roomdemo.repository.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    // Since our ViewModel class has a constructor parameter, we have to create a ViewModelFactory class for this ViewModel

    val subscribers = repository.subscribers

    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete : Subscriber

    val inputName = MutableLiveData<String>()

    val inputEmail = MutableLiveData<String>()

    val saveUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    // In different situations we might need to communicate with the view from the ViewModel to inform the user
    // about result, we might need to show toast message or a snackbar
    // One way of doing it is creating a separate interface with some functions written inside it then make the activity
    // implement that interface and invoking those functions from the ViewModel, but this is not a right approach to work with ViewModel
    // cause it violates the fundamentals of ViewModel. In MVVM the ViewModel should not knows about the views.
    // the ViewModel should not have an reference to the views.
    // We can use LiveData for events by creating single LiveEvent class but problem with single LiveEvent is
    // it is restricted to an observer, this live data will only send update points using event wrapper is the
    // recommended best practice.
    // A EventWrapper class allows us to explicitly consider the state of the event, so let's create it.

    private val statusMessage = MutableLiveData<Event<String>>()

    // This is a getter for "statusMessage" LiveData.
    val message: LiveData<Event<String>> get() = statusMessage // Since this is just a getter and we don't need to modify it that's why
    // we're using LiveData here instead of MutableLiveData

//    Here, get() is overriding the automatically-generated Kotlin getter function for the statusMessage property. So, instead of returning its own value, it returns the value of statusMessage.
//
//    Personally, I recommend this simpler syntax:
//    private val statusMessage = MutableLiveData<Event<String>>()
//    val message: LiveData<Event<String>> = statusMessage

//    The objective of either of these is to keep the mutability private, so consumers of this class do not accidentally update the MutableLiveData themselves.

    fun saveOrUpdate(){

        if(inputName.value == null || inputName.value == "") {

            statusMessage.value = Event("Please enter subscribers name.")

        } else if(inputEmail.value == null || inputEmail.value == "") {

            statusMessage.value = Event("Please enter subscribers email.")

        }  else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {

            statusMessage.value = Event("Please enter a correct email address.")

        } else {

            if (isUpdateOrDelete) {

                subscriberToUpdateOrDelete.name = inputName.value!!
                subscriberToUpdateOrDelete.email = inputEmail.value!!

                update(subscriberToUpdateOrDelete)

            } else {

                val name = inputName.value!!
                val email = inputEmail.value!!

                insert(Subscriber(0, name, email))
                // As we have declared id as a auto-incremental primary key we can just pass 0 while inserting to the subscriber entity
                // Room will just ignore it and add auto-incremented value.
                inputName.value = ""
                inputEmail.value = ""
            }

        }

    }

    fun clearAllOrDelete(){

        if(isUpdateOrDelete) {

            delete(subscriberToUpdateOrDelete)

        } else {
            clearAll()
        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber) {

        inputName.value = subscriber.name
        inputEmail.value = subscriber.email

        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber

        saveUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }

//    fun insert(subscriber: Subscriber){
    fun insert(subscriber: Subscriber): Job = viewModelScope.launch {
//            repository.insert(subscriber)
            val newRowId = repository.insert(subscriber)
            // If insert fails, Room will return row id as -1

            if(newRowId > -1) {
                statusMessage.value = Event("Subscriber inserted successfully, RowId: ${newRowId}.")
            } else {
                statusMessage.value = Event("Error occurred while inserting subscriber!")
            }
        }

    fun update(subscriber: Subscriber): Job = viewModelScope.launch {

        val noOfRows = repository.update(subscriber)

        if(noOfRows > 0) {

            inputName.value = ""
            inputEmail.value = ""

            isUpdateOrDelete = false
//        subscriberToUpdateOrDelete = null

            saveUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"

            statusMessage.value = Event("$noOfRows Subscriber updated successfully.")

        } else {

            statusMessage.value = Event("Error while updating the subscriber!.")
        }
    }

    fun delete(subscriber: Subscriber): Job = viewModelScope.launch {

        val noOfRowsDeleted = repository.delete(subscriber)

        if(noOfRowsDeleted > 0) {

            inputName.value = ""
            inputEmail.value = ""

            isUpdateOrDelete = false
//        subscriberToUpdateOrDelete = null

            saveUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"

            statusMessage.value = Event("$noOfRowsDeleted subscriber deleted successfully.")

        } else {

            statusMessage.value = Event("Error while deleting the record")

        }
    }

    fun clearAll(): Job = viewModelScope.launch {

        val noOfRowsDeleted = repository.deleteAll()

        if(noOfRowsDeleted > 0) {
            statusMessage.value = Event("All $noOfRowsDeleted subscribers deleted successfully.")
        } else {
            statusMessage.value = Event("Error occurred while clearing the records!")
        }
    }



}