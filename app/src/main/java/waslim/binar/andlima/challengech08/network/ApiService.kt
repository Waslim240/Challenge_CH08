package waslim.binar.andlima.challengech08.network

import retrofit2.http.GET
import waslim.binar.andlima.challengech08.model.film.DataFilmResponseItem
import waslim.binar.andlima.challengech08.model.user.DataUserResponseItem

interface ApiService {
    // get login
    @GET("user")
    suspend fun getDataUser() : List<DataUserResponseItem>

    // get list film
    @GET("film")
    suspend fun getDataFilm() : List<DataFilmResponseItem>
}