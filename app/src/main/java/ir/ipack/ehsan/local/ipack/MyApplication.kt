package ir.ipack.ehsan.local.ipack

import android.app.Application
import ir.ipack.ehsan.local.ipack.data.db.AppDatabase
import ir.ipack.ehsan.local.ipack.data.source.Repository
import ir.ipack.ehsan.local.ipack.data.source.local.LocalDataSource
import ir.ipack.ehsan.local.ipack.utils.AppAssets
import timber.log.Timber
import timber.log.Timber.DebugTree

class MyApplication : Application() {
    private val db: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }
    private val appAssets: AppAssets by lazy {
        AppAssets(this)
    }
    private val localDataSource: LocalDataSource by lazy {
        LocalDataSource(db, appAssets)
    }

    val repository: Repository by lazy {
        Repository(localDataSource)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}