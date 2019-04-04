package ir.ipack.ehsan.local.ipack.utils

import androidx.test.core.app.ApplicationProvider
import ir.ipack.ehsan.local.ipack.BaseRobolectricTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AppAssetsTest : BaseRobolectricTest() {

    private lateinit var appAssets: AppAssets
    @Before
    fun setUp() {
        appAssets = AppAssets(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun readAsString() {
        assertTrue(appAssets.readAsString(appAssets.getFile("offers.json")).length > 10)
    }
}