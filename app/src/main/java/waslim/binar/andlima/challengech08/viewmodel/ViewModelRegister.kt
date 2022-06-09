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
//    private val registerState = MutableStateFlow(emptyList<PostUser>())
//    val dataState : StateFlow<List<PostUser>>
//        get() = registerState
//
//    init {
//        viewModelScope.launch {
//            val register = registerRepository.postUser()
//            registerState.value = register
//        }
//    }

    private var liveDataRegister : MutableLiveData<PostUser> = MutableLiveData()

    fun register(email: String, password: String, username: String){
        viewModelScope.launch {
            registerRepository.postUser(email, password, username, liveDataRegister)
        }
    }

//    val email : String = ""
//    val password : String = ""
//    val username : String = ""
//    private val registerState = MutableStateFlow(emptyList<DataUserResponseItem>())
//    val dataState : StateFlow<List<DataUserResponseItem>>
//        get() = registerState
//
//    init {
//        viewModelScope.launch {
////            val register = registerRepository.postUser("", "", "")
////            registerState.value = register
//            val register = apiService.postUser(PostUser(email, password, username))
//            registerState.value = register
//        }
//    }

//
//    fun registerUser(email: String, password: String, username: String){
//        registerRepository.postUser(email, password, username)
//        registerState.value = registerUser(email, password, username)
//    }


}