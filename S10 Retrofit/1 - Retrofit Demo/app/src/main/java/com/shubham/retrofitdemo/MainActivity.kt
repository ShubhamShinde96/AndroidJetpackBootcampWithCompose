package com.shubham.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var retrofitInstance: AlbumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        retrofitInstance = RetrofitInstance.getRetrofitInstance()
            .create(AlbumService::class.java) // This returns album service retrofit instance.


//        getRequestWithQueryParameter()

//        getRequestWithPathParameters()

        uploadAlbum()

    }


    private fun getRequestWithQueryParameter() {

        // now using a coroutine LiveData builder we'll get the retrofit response as a live data.

        val responseLiveData : LiveData<Response<Albums>> = liveData {

            // this liveData { } is kotlin coroutine liveData builder

//            val response = retrofitInstance.getAlbums()
            val response = retrofitInstance.getSortedAlbums(3)

            // Now we need to emit() this so that it will return the LiveData builder block as a LiveData

            emit(response)
        }

        responseLiveData.observe(this@MainActivity, Observer {

            val albumsList = it.body()?.listIterator()

            if(albumsList != null) {

                while (albumsList.hasNext()) {

                    val albumsItem = albumsList.next()
                    Log.i("MYTAG", albumsItem.title)

                    val result = "Album id: ${albumsItem.title}\nAlbum Id: ${albumsItem.id}\n" +
                            "User Id: ${albumsItem.userId}\n\n\n"

                    textView.append(result)

                }

            }
        })
    }

    private fun getRequestWithPathParameters() {

        // Path parameter example
        val pathResponse : LiveData<Response<AlbumsItem>> = liveData {

            val response = retrofitInstance.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this@MainActivity, Observer {

            val title = it.body()?.title

            Toast.makeText(this@MainActivity, "Title: ${title}", Toast.LENGTH_LONG).show()
        })
    }


    private fun uploadAlbum() {

        val album = AlbumsItem(101, "This is my title for testing purpose", 3)

        val postResponse : LiveData<Response<AlbumsItem>> = liveData {
            val response = retrofitInstance.uploadAlbum(album)
            emit(response)
        }

        postResponse.observe(this@MainActivity, Observer {

            val receivedAlbumsItem = it.body()

            val result = "Album id: ${receivedAlbumsItem?.title}\nAlbum Id: ${receivedAlbumsItem?.id}\n" +
                    "User Id: ${receivedAlbumsItem?.userId}\n\n\n"

            textView.append(result)
        })

    }

}



























