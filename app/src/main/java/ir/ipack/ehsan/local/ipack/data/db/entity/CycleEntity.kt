package ir.ipack.ehsan.local.ipack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum
import ir.ipack.ehsan.local.ipack.utils.UnitEnum

@Entity(tableName = "cycle")
class CycleEntity(
    @PrimaryKey @ColumnInfo(name = "type") val type: CycleTypeEnum?,
    @ColumnInfo(name = "unit") val unit: UnitEnum?,
    @ColumnInfo(name = "cycle_image") val cycleImage: Int?,
    @ColumnInfo(name = "used") val used: Double?,
    @ColumnInfo(name = "limit") val limit: Int?
)