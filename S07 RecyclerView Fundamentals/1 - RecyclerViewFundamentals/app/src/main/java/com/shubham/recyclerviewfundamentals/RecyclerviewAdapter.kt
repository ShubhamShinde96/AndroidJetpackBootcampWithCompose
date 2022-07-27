package com.shubham.recyclerviewfundamentals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerviewAdapter(private val fruitsList: List<Fruit>, private val clickListener:(Fruit)->Unit): RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)

        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(fruitsList[position], clickListener)

        // For setting up click listeners we're going to use advance language features of kolin like
        // higher order functions & lambda expressions which is much easier than it has been in java

        // higher order function is a function which accept & returns another function

        // In kotlin functions are treated like firstClass citizens, that means we can assign them
        // to variables or we can pass them to another function as parameters by using lambda expression
        // or function references.



    }

    override fun getItemCount(): Int  = fruitsList.size

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTv: TextView = view.findViewById<TextView>(R.id.name_textView)

        fun bind(fruit: Fruit, clickListener:(Fruit)->Unit) {

            nameTv.text = fruit.name

            itemView.setOnClickListener {
                clickListener(fruit)
            }

        }
    }

}