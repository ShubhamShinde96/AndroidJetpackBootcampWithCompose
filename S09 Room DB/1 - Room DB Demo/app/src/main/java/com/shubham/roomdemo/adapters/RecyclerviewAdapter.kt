package com.shubham.roomdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shubham.roomdemo.R
import com.shubham.roomdemo.databinding.ListItemBinding
import com.shubham.roomdemo.db.Subscriber

//class RecyclerviewAdapter(private val subscribers: List<Subscriber>,
class RecyclerviewAdapter(private val clickListener : (Subscriber) -> Unit)
        : RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {


    private val subscribersList = ArrayList<Subscriber>()

    class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {

            binding.nameTv.text = subscriber.name
            binding.emailTv.text = subscriber.email
            binding.listItemLinLayout.setOnClickListener {
                clickListener(subscriber)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false)

        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(subscribersList[position], clickListener)

    }

    override fun getItemCount(): Int = subscribersList.size

    fun setList(subscribers: List<Subscriber>) {

        subscribersList.clear()
        subscribersList.addAll(subscribers)

    }

}