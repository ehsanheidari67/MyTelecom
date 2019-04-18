package ir.ipack.ehsan.local.ipack.data.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import ir.ipack.ehsan.local.ipack.data.db.dao.BasePlanDao
import ir.ipack.ehsan.local.ipack.data.db.dao.CycleDao
import ir.ipack.ehsan.local.ipack.data.db.dao.OfferDao
import ir.ipack.ehsan.local.ipack.data.db.dao.UsageDao
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.OfferEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.local.DataPersistence
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
import java.util.concurrent.Executors

@Database(
    entities = [BasePlanEntity::class, CycleEntity::class, OfferEntity::class, UsageEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(), DataPersistence {
    abstract fun basePlanDao(): BasePlanDao
    abstract fun cycleDao(): CycleDao
    abstract fun offerDao(): OfferDao
    abstract fun usageDao(): UsageDao

    companion object {

        private const val DATABASE_NAME = "AppDatabase.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadExecutor().execute {
                        AppDatabase.getInstance(context).apply {
                            initialDb(this)
                        }
                    }
                }
            })
                .allowMainThreadQueries() // TODO: Run the queries in a background thread in a future PR
                .build()

        private fun initialDb(db: AppDatabase) {
            db.basePlanDao().insertBasePlan(
                BasePlanEntity(
                    baseCost = PlanConstants.INITIAL_BASE_COST,
                    addonCost = PlanConstants.INITIAL_ADDON_COST
                )
            )
            db.usageDao().insert(
                UsageEntity(
                    total = 200, isUnlimited = true, type = CycleTypeEnum.TALK, incoming = 29, outgoing = 96
                )
            )
            db.usageDao().insert(
                UsageEntity(
                    total = 350, isUnlimited = false, type = CycleTypeEnum.TEXT, incoming = 186, outgoing = 164
                )
            )
            db.usageDao().insert(
                UsageEntity(
                    appName = "Facebook", imageName = "social_dark_gray", type = CycleTypeEnum.INTERNET, used = 0.75,
                    limit = 2, isUnlimited = false
                )
            )
            db.usageDao().insert(
                UsageEntity(
                    appName = "YouTube", imageName = "video_dark_gray", type = CycleTypeEnum.INTERNET, used = 0.3,
                    limit = 2, isUnlimited = false
                )
            )
            db.usageDao().insert(
                UsageEntity(
                    appName = "WhatsApp", imageName = "social_dark_gray", type = CycleTypeEnum.INTERNET, used = 0.2,
                    limit = 2, isUnlimited = false
                )
            )
        }
    }

    override fun getUsageByType(cycleTypeEnum: CycleTypeEnum) = usageDao().getByType(cycleTypeEnum)
    override fun getUsageByTypeLive(cycleTypeEnum: CycleTypeEnum): LiveData<List<UsageEntity>> =
        usageDao().getByTypeLive(cycleTypeEnum)
}