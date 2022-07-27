package com.shubham.retrofitdemo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    // Retrofit library has created on top of the OkHttp library, Retrofit uses classes of the OkHttp to perform
    // the network operations.

    companion object {

        // used companion object here cause in kotlin a companion object initializes when the class is loaded for
        // the first time so if we create the instance inside companion object then we'll easily get it using a class name.

        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY // This body level logs the request and response lines and their respective headers and bodies of the network operation.

            // There are other levels like 1] BASIC: logs request and response lines only 2] HEADER: logs the request and response line and their respective headers
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
            // We don't need to manually add timeout if it's not needed for you
            // If we don't specify then retrofit will use default timeout time which is 10 seconds for each timeout
            // Connect Timeout is the time period in which our app needs to establish a connection with the server.
            // Read Timeout is a maximum time-gap between arrivals of 2 data packets when waiting for the servers response.
            // Write Timeout is a maximum time-gap between sending 2 data packets to the server.

            // These connect read and write timeouts are tentatively added, you should add these values considering your needs, do some research on these values.

        }.build()

        // Logs will be logged with the TAG = "OkHttp"

        val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun getRetrofitInstance() : Retrofit {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

    }

    // This network logging interceptor belongs to the OkHttp library.

}