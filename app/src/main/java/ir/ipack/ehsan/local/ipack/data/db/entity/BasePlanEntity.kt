package ir.ipack.ehsan.local.ipack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "base_plan")
data class BasePlanEntity(
    @PrimaryKey @ColumnInfo(name = "base_plan_id") val basePlanId: String = "1",
    @ColumnInfo(name = "base_cost") var baseCost: Double = 0.0,
    @ColumnInfo(name = "addon_cost") val addonCost: Double = 0.0
) {
    val totalCost: Double
        get() = baseCost + addonCost
}