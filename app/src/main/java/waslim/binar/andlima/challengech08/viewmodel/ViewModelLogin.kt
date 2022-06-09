package waslim.binar.andlima.challengech08.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import waslim.binar.andlima.challengech08.model.user.DataUserResponseItem
import waslim.binar.andlima.challengech08.repository.LoginRepository
import javax.inject.Inject

@HiltViewModel
class ViewModelLogin @Inject constructor(private val loginRepository: LoginRepository) : ViewModel(){
    private val loginState = MutableStateFlow(emptyList<DataUserResponseItem>())
    val dataState : StateFlow<List<DataUserResponseItem>>
        get() = loginState

    init {
        viewModelScope.launch {
            val login = loginRepository.getUser()
            loginState.value = login
        }
    }
}