package teamwork.task.rest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val retrofit: Retrofit

    companion object {
        private var mInstance: RetrofitClient? = null

        fun getInstance(): RetrofitClient {
            if (mInstance == null) {
                mInstance = RetrofitClient()
            }
            return mInstance!!
        }
    }

    val api: Api
        get() {
            return retrofit.create(Api::class.java)
        }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

}