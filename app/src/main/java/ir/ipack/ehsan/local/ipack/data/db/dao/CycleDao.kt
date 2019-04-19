package ir.ipack.ehsan.local.ipack.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum

@Dao
interface CycleDao {
    @Query("select * from cycle")
    fun getAll(): List<CycleEntity>

    @Query("select * from cycle where type=:cycleType")
    fun getCycleByType(cycleType: CycleTypeEnum): LiveData<List<CycleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cycleEntity: CycleEntity)
}