package com.shubham.roomdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.roomdemo.adapters.RecyclerviewAdapter
import com.shubham.roomdemo.databinding.ActivityMainBinding
import com.shubham.roomdemo.db.Subscriber
import com.shubham.roomdemo.db.SubscriberDatabase
import com.shubham.roomdemo.models.SubscriberViewModel
import com.shubham.roomdemo.models.SubscriberViewModelFactory
import com.shubham.roomdemo.repository.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var subscriberViewModelFactory: SubscriberViewModelFactory
    private lateinit var adapter: RecyclerviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)

        subscriberViewModelFactory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, subscriberViewModelFactory).get(SubscriberViewModel::class.java)


        binding.mySubscriberViewModel = subscriberViewModel

        binding.lifecycleOwner = this@MainActivity

//        displaySubscribersList()
        initRecyclerView()

        subscriberViewModel.message.observe(this@MainActivity, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
            }

                    //getContentIfNotHandled() this avoids the repetitions.
        })

        // Room creates a table for each class annotates with @entity

    }

    private fun displaySubscribersList() {

        // check out what is FLOW in kotlin, if we've used it then we have to convert FLOW to LiveData
        subscriberViewModel.subscribers.observe(this@MainActivity, Observer {

            // To implement click event, we're going to use kotlin higher order function & lambda expression
            // Here we're going to use lambda expression to pass the function as an argument.

            adapter.setList(it)
            adapter.notifyDataSetChanged()

        })
    }


    private fun initRecyclerView() {

        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        adapter = RecyclerviewAdapter({selectedItem : Subscriber -> listItemClicked(selectedItem)})

        binding.subscriberRecyclerView.adapter = adapter
        displaySubscribersList()
    }

    private fun listItemClicked(subscriber: Subscriber) {

//        Toast.makeText(this@MainActivity, "${subscriber.name}", Toast.LENGTH_SHORT).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }

}