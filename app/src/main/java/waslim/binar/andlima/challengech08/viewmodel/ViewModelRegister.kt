package waslim.binar.andlima.challengech08.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import waslim.binar.andlima.challengech08.model.user.PostUser
import waslim.binar.andlima.challengech08.repository.RegisterRepository
import javax.inject.Inject

@HiltViewModel
class ViewModelRegister @Inject constructor(private val registerRepository: RegisterRepository) : ViewModel(){

    private var liveDataRegister : MutableLiveData<PostUser> = MutableLiveData()

    fun register(email : String, password : String, username: String) {
        viewModelScope.launch {
            registerRepository.postUser(email, password, username, liveDataRegister)
        }

    }


}