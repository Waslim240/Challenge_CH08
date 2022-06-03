package waslim.binar.andlima.challengech08.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import waslim.binar.andlima.challengech08.model.film.DataFilmResponseItem
import waslim.binar.andlima.challengech08.repository.FilmRepository
import javax.inject.Inject

@HiltViewModel
class ViewModelFilm @Inject constructor(private val filmRepository: FilmRepository) : ViewModel(){
    private val filmState = MutableStateFlow(emptyList<DataFilmResponseItem>())
    val dataState : StateFlow<List<DataFilmResponseItem>>
    get() = filmState

    init {
        viewModelScope.launch {
            val film = filmRepository.getFilm()
            filmState.value = film
        }
    }
}