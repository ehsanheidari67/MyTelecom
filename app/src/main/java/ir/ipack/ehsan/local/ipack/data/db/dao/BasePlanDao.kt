package ir.ipack.ehsan.local.ipack.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity

@Dao
interface BasePlanDao {
    @Query("select * from base_plan")
    fun getAll(): List<BasePlanEntity>

    @Query("select * from base_plan")
    fun getBasePlanLive(): LiveData<List<BasePlanEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBasePlan(basePlanEntity: BasePlanEntity)

    @Query("UPDATE base_plan SET base_cost = base_cost + :changedAmount")
    fun updateBasePlan(changedAmount: Int)
}