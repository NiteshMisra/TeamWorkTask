package teamwork.task.rest

import retrofit2.Response
import retrofit2.http.GET
import teamwork.task.models.PhotosModel

interface Api {

    @GET("/photos")
    suspend fun getPhotos() : Response<List<PhotosModel>>

}