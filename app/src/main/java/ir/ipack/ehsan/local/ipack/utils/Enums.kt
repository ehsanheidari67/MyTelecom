package ir.ipack.ehsan.local.ipack.utils

enum class CycleTypeEnum(val type: Int) {
    INTERNET(1),
    TALK(2),
    TEXT(3)
}

enum class UnitEnum(val unit: String) {
    GB("GB"),
    MIN("min"),
    SMS("SMS")
}