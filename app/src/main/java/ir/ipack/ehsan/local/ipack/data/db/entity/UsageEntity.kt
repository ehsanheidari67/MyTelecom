package ir.ipack.ehsan.local.ipack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum
import java.util.UUID

@Entity(tableName = "usage")
data class UsageEntity(
    @PrimaryKey @ColumnInfo(name = "usage_id") val usageId: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "total") val total: Int? = null,
    @ColumnInfo(name = "app_name") val appName: String? = null,
    @ColumnInfo(name = "usage_image") var usageImage: Int? = null,
    @ColumnInfo(name = "used_data") val usedData: String? = null,
    @ColumnInfo(name = "used") val used: Double? = null,
    @ColumnInfo(name = "limit") val limit: Int? = null,
    @ColumnInfo(name = "is_unlimited") val isUnlimited: Boolean = false,
    @ColumnInfo(name = "type") val type: CycleTypeEnum?,
    @ColumnInfo(name = "incoming") val incoming: Int? = null,
    @ColumnInfo(name = "outgoing") val outgoing: Int? = null,
    @ColumnInfo(name = "image_name") val imageName: String? = null
) {
    val seekBarProgress: Int
        get() {
            return if (used != null && limit != null) {
                (used * 100 / limit).toInt()
            } else {
                0
            }
        }
}