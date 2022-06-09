package waslim.binar.andlima.challengech08.repository

import waslim.binar.andlima.challengech08.model.user.DataUserResponseItem
import waslim.binar.andlima.challengech08.network.ApiService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: ApiService){
    suspend fun getUser() : List<DataUserResponseItem>{
        return apiService.getDataLogin()
    }
}