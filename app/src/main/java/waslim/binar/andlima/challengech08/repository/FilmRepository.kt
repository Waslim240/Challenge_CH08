package waslim.binar.andlima.challengech08.repository

import waslim.binar.andlima.challengech08.model.film.DataFilmResponseItem
import waslim.binar.andlima.challengech08.network.ApiService
import javax.inject.Inject

class FilmRepository @Inject constructor(private val apiService: ApiService){
    suspend fun getFilm() : List<DataFilmResponseItem>{
        return apiService.getDataFilm()
    }
}