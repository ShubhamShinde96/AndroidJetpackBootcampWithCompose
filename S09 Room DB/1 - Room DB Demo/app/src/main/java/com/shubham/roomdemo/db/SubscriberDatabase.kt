package com.shubham.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Subscriber::class],
    version = 1
) // here we need to provide the list of entity classes. Then the version no, it is imp when we are migrating the DB from one version to another.
abstract class SubscriberDatabase : RoomDatabase() {

    // Now defining the reference to the DAO interface
    abstract val subscriberDAO: SubscriberDAO
    // here we have only one entity class and corresponding DAO interface, if we had more entity classes then we would have listed them here and defined the references for corresponding DAOs

    // You should always allow/use only one instance of your database object in the entire application, this is to avoid unnecessary errors and issues related to multiple instances of DB
    // therefore lets create a singleton here.
    // as in kotlin we create singletons as a companion object
    companion object {

        @Volatile // Volatile annotation makes the field immediately visible to other threads.
        private var INSTANCE: SubscriberDatabase? = null

        fun getInstance(context: Context): SubscriberDatabase {
            synchronized(this) {   // scroll below to check about this synchronized(thia)
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build() // third param is the database name
                }

                return instance
            }
        }
    }


}

//

//      synchronized methods enable a simple strategy for preventing thread interference and memory consistency errors:
//      if an object is visible to more than one thread, all reads or writes to that object's variables are done
//      through synchronized methods.

// In a very, very small nutshell: When you have two threads that are reading and writing to the same 'resource',
// say a variable named foo, you need to ensure that these threads access the variable in an atomic way.
// Without the synchronized keyword, your thread 1 may not see the change thread 2 made to foo, or worse,
// it may only be half changed. This would not be what you logically expect.









































