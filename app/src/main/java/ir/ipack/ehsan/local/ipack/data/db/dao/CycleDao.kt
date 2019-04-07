package ir.ipack.ehsan.local.ipack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity

@Dao
interface CycleDao {
    @Query("select * from cycle")
    fun getAll(): List<CycleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cycleEntity: CycleEntity)
}