package ir.ipack.ehsan.local.ipack.data.db

import androidx.room.TypeConverter
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum
import ir.ipack.ehsan.local.ipack.utils.UnitEnum

class Converters {
    @TypeConverter
    fun toUnitEnum(unit: String?): UnitEnum? =
        UnitEnum.values().firstOrNull {
            it.unit == unit
        }

    @TypeConverter
    fun unitEnumToValue(enum: UnitEnum?): String? = enum?.unit

    @TypeConverter
    fun toCycleTypeEnum(type: Int?): CycleTypeEnum? =
        CycleTypeEnum.values().firstOrNull {
            it.type == type
        }

    @TypeConverter
    fun cycleTypeEnumToValue(enum: CycleTypeEnum?): Int? = enum?.type
}