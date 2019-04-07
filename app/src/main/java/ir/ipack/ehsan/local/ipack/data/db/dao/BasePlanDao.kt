package ir.ipack.ehsan.local.ipack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity

@Dao
interface BasePlanDao {
    @Query("select * from base_plan")
    fun getAll(): List<BasePlanEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBasePlan(basePlanEntity: BasePlanEntity)
}