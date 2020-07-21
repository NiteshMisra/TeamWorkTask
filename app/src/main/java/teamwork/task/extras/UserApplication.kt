package teamwork.task.extras

import android.app.Application
import android.content.Context

class UserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this@UserApplication
    }

    companion object {

        @get:Synchronized
        var instance: UserApplication? = null
            private set

        fun getContext() : UserApplication = instance!!.applicationContext as UserApplication

    }

}