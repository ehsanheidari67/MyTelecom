package ir.ipack.ehsan.local.ipack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "base_plan")
data class BasePlanEntity(
    @PrimaryKey @ColumnInfo(name = "base_plan_id") val basePlanId: String = "1",
    @ColumnInfo(name = "base_cost") val baseCost: Double?,
    @ColumnInfo(name = "addon_cost") val addonCost: Double?
)