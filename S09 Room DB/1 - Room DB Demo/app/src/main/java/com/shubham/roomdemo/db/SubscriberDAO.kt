package com.shubham.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {

    @Insert//(onConflict = OnConflictStrategy.REPLACE) // "onConflict = OnConflictStrategy.REPLACE" this will basically replace the row if the id we're passing is same with any existing entry, if we set it to "OnConflictStrategy.IGNORE" then it will ignore the complicit
    suspend fun insertSubscriber(subscriber : Subscriber): Long
    // making this function suspending because room support coroutines and performing DB operation on main
    // thread will block the UI so best way is to use suspend function, although you can use other method of
    // making it run on background thread such as handlers, AsyncTask or anything else, but since coroutines are easy to use we are going with it.

    // We can also write this function, adding a list of subscribers as the parameter to update an entire list at once
    @Update // we can also write this function with an int return type, this will return the number of rows updated in the DB
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    // Same as the update we can delete list of subscribers by passing list to delete records, and we can also expect an int return type to get to know number of rows deleted.
    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    // With @Query annotation, room allows us to include a SQL query that would run when the function is called.
    // Most IMP is that this query will be verified at compile time by Room to ensure that it works well with the DB
    // So there will be no database query related runtime errors. this is the main advantage of using Room.

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll() : Int


    // Room facilitate us to get data from the database table as a live data of list of entities, these queries are called
    // asynchronous queries, because for these queries which has a LiveData as a return value, room always run them on a background thread by itself.
    // So we don't have to use Coroutines of any other threading method to run this query on a background thread.
    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers(): LiveData<List<Subscriber>> // Notice we didn't add suspend modifier for this fun, why is that?
    // Because we don't need to execute this fun in a background thread using Coroutine, Room will automatically do this for us as we're getting LiveData

    /*@Insert
    suspend fun insertSubscriber2(subscriber : Subscriber) : Long // Sometimes you may want to get a return value for confirmation, we will get new row id
    // as a return value of type long, the type should be always long or array or list of long values

    // To insert multiple subscriber objects
    @Insert
    suspend fun insertSubscribers(subscriber1 : Subscriber, subscriber2 : Subscriber, subscriber3 : Subscriber) : List<Long>


    // To insert list of subscriber objects
    @Insert
    fun insertSubscribers(subscriber: List<Subscriber>) : List<Long>

    // To insert list of subscriber objects as well as list of objects at the same time
    @Insert
    fun insertSubscribersWitList(subscriber: Subscriber, subscriberList: List<Subscriber>) : List<Long>*/


}