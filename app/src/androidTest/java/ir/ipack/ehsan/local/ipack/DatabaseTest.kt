package ir.ipack.ehsan.local.ipack

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import ir.ipack.ehsan.local.ipack.data.db.AppDatabase
import ir.ipack.ehsan.local.ipack.data.db.dao.BasePlanDao
import ir.ipack.ehsan.local.ipack.data.db.dao.CycleDao
import ir.ipack.ehsan.local.ipack.data.db.dao.OfferDao
import ir.ipack.ehsan.local.ipack.data.db.dao.UsageDao
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum
import ir.ipack.ehsan.local.ipack.utils.UnitEnum
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DatabaseTest {
    private lateinit var db: AppDatabase
    private lateinit var basePlanDao: BasePlanDao
    private lateinit var cycleDao: CycleDao
    private lateinit var offerDao: OfferDao
    private lateinit var usageDao: UsageDao

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java
        ).build()
        basePlanDao = db.basePlanDao()
        cycleDao = db.cycleDao()
        usageDao = db.usageDao()
    }

    @Test
    fun createAndRetrieveBasePlan() {
        basePlanDao.insertBasePlan(BasePlanEntity(baseCost = 100.0, addonCost = 5.0))
        basePlanDao.insertBasePlan(BasePlanEntity(baseCost = 150.0, addonCost = 4.0))
        basePlanDao.insertBasePlan(BasePlanEntity(baseCost = 125.0, addonCost = 6.0))
        assertEquals(1, basePlanDao.getAll().size)
        assertEquals(125.0, basePlanDao.getAll().firstOrNull()?.baseCost)
    }

    @Test
    fun createAndRetrieveCycle() {
        cycleDao.insert(CycleEntity(CycleTypeEnum.INTERNET, UnitEnum.GB, null, 10.0, 100))
        cycleDao.insert(CycleEntity(CycleTypeEnum.TALK, UnitEnum.MIN, null, 5.0, 20))
        cycleDao.insert(CycleEntity(CycleTypeEnum.TEXT, UnitEnum.SMS, null, 84.0, 120))
        cycleDao.insert(CycleEntity(CycleTypeEnum.INTERNET, UnitEnum.GB, null, 11.0, 106))
        cycleDao.insert(CycleEntity(CycleTypeEnum.TEXT, UnitEnum.MIN, null, 84.0, 120))
        assertEquals(3, cycleDao.getAll().size)
        assertEquals(11.0, cycleDao.getAll().filter {
            it.type == CycleTypeEnum.INTERNET
        }.firstOrNull()?.used)
    }

    @Test
    fun createAndRetrieveUsage() {
        usageDao.insert(
            UsageEntity(
                appName = "Facebook", imageName = "social_dark_gray", type = CycleTypeEnum.INTERNET, used = 0.75,
                limit = 2, isUnlimited = false
            )
        )
        assertEquals("Facebook", usageDao.getAll().firstOrNull()?.appName)
    }

    @After
    fun closeDb() {
        db.close()
    }
}