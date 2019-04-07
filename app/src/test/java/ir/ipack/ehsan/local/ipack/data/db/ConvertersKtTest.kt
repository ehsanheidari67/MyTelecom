package ir.ipack.ehsan.local.ipack.data.db

import ir.ipack.ehsan.local.ipack.utils.UnitEnum
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ConvertersKtTest {

    private lateinit var converters: Converters
    @Before
    fun setUp() {
        converters = Converters()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `toUnitEnum GB`() {
        assertEquals(UnitEnum.GB, converters.toUnitEnum("GB"))
    }

    @Test
    fun `toUnitEnum min`() {
        assertEquals(UnitEnum.MIN, converters.toUnitEnum("min"))
    }

    @Test
    fun `toUnitEnum SMS`() {
        assertEquals(UnitEnum.SMS, converters.toUnitEnum("SMS"))
    }

    @Test
    fun `toUnitEnum wrong value`() {
        assertNull(converters.toUnitEnum("abc"))
    }
}