package ir.ipack.ehsan.local.ipack.data.db

import android.content.Context
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
import java.util.concurrent.Executors

@Database(
    entities = [BasePlanEntity::class, CycleEntity::class, OfferEntity::class, UsageEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
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
                            insertInitialConfig(this)
                        }
                    }
                }
            }).build()

        private fun insertInitialConfig(db: AppDatabase) {
            // TODO: Initial DB in a future PR
        }
    }
}