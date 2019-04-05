package ir.ipack.ehsan.local.ipack

import android.app.Application
import ir.ipack.ehsan.local.ipack.data.source.Repository
import ir.ipack.ehsan.local.ipack.data.source.local.LocalDataSource
import ir.ipack.ehsan.local.ipack.utils.AppAssets

class MyApplication : Application() {
    private val appAssets: AppAssets by lazy {
        AppAssets(this)
    }
    private val localDataSource: LocalDataSource by lazy {
        LocalDataSource(appAssets)
    }

    val repository: Repository by lazy {
        Repository(localDataSource)
    }
}