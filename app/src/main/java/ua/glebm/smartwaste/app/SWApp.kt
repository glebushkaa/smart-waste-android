package ua.glebm.smartwaste.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

@HiltAndroidApp
class SWApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        val tree = Timber.DebugTree()
        Timber.plant(tree)
    }
}
