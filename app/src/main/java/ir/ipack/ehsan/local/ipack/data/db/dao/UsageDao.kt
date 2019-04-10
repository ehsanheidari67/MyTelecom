package ir.ipack.ehsan.local.ipack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum

@Dao
interface UsageDao {
    @Query("select * from usage")
    fun getAll(): List<UsageEntity>

    @Query("select * from usage where type=:cycleType")
    fun getByType(cycleType: CycleTypeEnum): List<UsageEntity>

    @Insert
    fun insert(usageEntity: UsageEntity)
}