package waslim.binar.andlima.challengech08.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import waslim.binar.andlima.challengech08.model.film.DataFilmResponseItem
import waslim.binar.andlima.challengech08.model.user.DataUserResponseItem
import waslim.binar.andlima.challengech08.model.user.PostUser

interface ApiService {
    // get login
    @GET("user")
    suspend fun getDataLogin() : List<DataUserResponseItem>

    @POST("user")
    fun postDataRegister(@Body postUser: PostUser) : Call<PostUser>

    // get list film
    @GET("film")
    suspend fun getDataFilm() : List<DataFilmResponseItem>
}