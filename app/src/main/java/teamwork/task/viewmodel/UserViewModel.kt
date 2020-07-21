package teamwork.task.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import teamwork.task.database.UserDatabase
import teamwork.task.models.PhotosModel
import teamwork.task.models.User
import teamwork.task.rest.Coroutines
import teamwork.task.rest.RetrofitClient

class UserViewModel : ViewModel() {

    fun getPhotos() :  LiveData<List<PhotosModel>> {
        val data : MutableLiveData<List<PhotosModel>> = MutableLiveData()
        Coroutines.io {
            val response = RetrofitClient.getInstance().api.getPhotos()
            if (response.isSuccessful){
                data.postValue(response.body())
            }else{
                data.postValue(null)
            }
        }
        return data
    }


    fun getUser(context : Context, userEmail : String) : LiveData<User> {
        val data : MutableLiveData<User> = MutableLiveData()
        val db = UserDatabase.getInstance(context)
        Coroutines.io {
            data.postValue(db.playListDao().getUserDetail(userEmail))
        }
        return data
    }


    fun signInUser(context : Context, email : String, password : String) : LiveData<User?> {
        val data : MutableLiveData<User?> = MutableLiveData()
        val db = UserDatabase.getInstance(context)
        Coroutines.io {
            data.postValue(db.playListDao().signIn(email,password))
        }
        return data
    }


    fun signUpUser(context: Context, user: User) : LiveData<User> {
        val data : MutableLiveData<User> = MutableLiveData()
        Coroutines.io {
            val db = UserDatabase.getInstance(context)
            if (db.playListDao().checkUserExist(user.userEmail) == 0){
                db.playListDao().insert(user)
                data.postValue(db.playListDao().signIn(user.userEmail,user.userPassword))
            }else{
                data.postValue(null)
            }
        }
        return data
    }

}