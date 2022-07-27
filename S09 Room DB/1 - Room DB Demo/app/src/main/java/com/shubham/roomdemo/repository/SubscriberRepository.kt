package com.shubham.roomdemo.repository

import com.shubham.roomdemo.db.Subscriber
import com.shubham.roomdemo.db.SubscriberDAO

class SubscriberRepository(private val dao: SubscriberDAO) {

    val subscribers = dao.getAllSubscribers()

//    suspend fun insert(subscriber: Subscriber) {
    suspend fun insert(subscriber: Subscriber) : Long {

//        dao.insertSubscriber(subscriber)
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber) : Int {

        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber) : Int {

        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll() : Int{

        return dao.deleteAll()
    }

}