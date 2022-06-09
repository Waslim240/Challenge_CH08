package waslim.binar.andlima.challengech08.repository

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import waslim.binar.andlima.challengech08.model.user.PostUser
import waslim.binar.andlima.challengech08.network.ApiService
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val apiService: ApiService){
//    suspend fun postUser() : List<PostUser> {
//        return apiService.postUser()
//    }

    fun postUser(email :String, password: String, username : String, liveData: MutableLiveData<PostUser>){
        val retrofit : Call<PostUser> = apiService.postDataRegister(PostUser(email, password, username))
        retrofit.enqueue(object : Callback<PostUser> {
            override fun onResponse(
                call: Call<PostUser>,
                response: Response<PostUser>
            ) {
                if (response.isSuccessful){
                    liveData.postValue(response.body())
                }else{
                    liveData.postValue(null)


                }
            }

            override fun onFailure(call: Call<PostUser>, t: Throwable) {
                liveData.postValue(null)
            }

        })

    }
}