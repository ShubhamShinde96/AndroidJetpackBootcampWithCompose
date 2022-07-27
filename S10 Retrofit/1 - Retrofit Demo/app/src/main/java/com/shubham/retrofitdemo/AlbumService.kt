package com.shubham.retrofitdemo

import retrofit2.Response
import retrofit2.http.*

interface AlbumService {

    // used suspend cause we're going to use coroutines with retrofit
    @GET("/albums") //@GET cause we are using GET request here and inside () you have to mention the endpoint of the url
    suspend fun getAlbums() : Response<Albums>

    //Response<Album> -> Retrofit always give the result as a retrofit response object


    // this is how to use query parameters with retrofit
    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int) : Response<Albums>

//     https://jsonplaceholder.typicode.com/albums/3  ->  /3 is called "path parameter"
    // These path parameter depends on the how the API is implemented this functionality
    // Since JSONPlaceholder API creators have implemented the facility to get a album object using the id as a
    // path parameter we were able to get the object in a response like below

    /*
     {
        "userId": 1,
        "id": 3,
        "title": "omnis laborum odio"
     }*/

    // So if we want to use path parameters then follow the below code

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int) : Response<AlbumsItem>

    @POST("/albums")   // This albums item will get sent as the body of the POST request so we have annotated it wih the "Body" annotation.
    suspend fun uploadAlbum(@Body albumsItem: AlbumsItem) : Response<AlbumsItem>

}