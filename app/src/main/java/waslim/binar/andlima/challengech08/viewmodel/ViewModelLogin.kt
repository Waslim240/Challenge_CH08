package waslim.binar.andlima.challengech08.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import waslim.binar.andlima.challengech08.model.user.DataUserResponseItem
import waslim.binar.andlima.challengech08.network.ApiService
import javax.inject.Inject

@HiltViewModel
class ViewModelLogin @Inject constructor(apiService: ApiService) : ViewModel(){

    private val userLogin = MutableLiveData<List<DataUserResponseItem>>()

    init {
        viewModelScope.launch {
            val dataLogin = apiService.getDataUser()

            userLogin.value = dataLogin
        }
    }

}