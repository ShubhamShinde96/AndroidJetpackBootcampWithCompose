package com.shubham.recyclerviewfundamentals

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    val fruitsList = listOf(Fruit("Mango", "Shubham"),
        Fruit("Apple", "Sujay"), Fruit("Banana", "Kshitij"),
        Fruit("Guava", "Om"), Fruit("Orange", "Ajinkya"),
        Fruit("Pear", "Rahul"), Fruit("Kiwi", "Sumit"),
        Fruit("Grapes", "Amar"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.setBackgroundColor(Color.YELLOW)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        // We're going to pass listItemClicked() using lambda expression to the adapter
        // We are going to write a lambda expression as a constructor argument.

//        recyclerView.adapter = RecyclerviewAdapter(fruitsList, { selectedFruitItem: Fruit -> listItemCLicked(selectedFruitItem)})

        recyclerView.adapter = RecyclerviewAdapter(fruitsList) { selectedFruitItem: Fruit ->
            listItemCLicked(
                selectedFruitItem
            )
        }


    }


    private fun listItemCLicked(fruit: Fruit) {
        Toast.makeText(this@MainActivity, "Supplier: ${fruit.supplier}", Toast.LENGTH_SHORT).show()
    }

}