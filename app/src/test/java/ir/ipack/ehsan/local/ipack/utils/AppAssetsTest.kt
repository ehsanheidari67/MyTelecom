package ir.ipack.ehsan.local.ipack.utils

import androidx.test.core.app.ApplicationProvider
import ir.ipack.ehsan.local.ipack.BaseRobolectricTest
import org.junit.Before

class AppAssetsTest : BaseRobolectricTest() {

    private lateinit var appAssets: AppAssets
    @Before
    fun setUp() {
        appAssets = AppAssets(ApplicationProvider.getApplicationContext())
    }
}