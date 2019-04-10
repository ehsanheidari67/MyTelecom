package ir.ipack.ehsan.local.ipack.utils

object PlanConstants {
    const val DATA = 1
    const val TALK = 2
    const val TEXT = 3
    const val DATA_UNIT = "GB" // gigabytes
    const val TALK_UNIT = "min" // minutes
    const val TEXT_UNIT = "SMS" // texts
    const val MIN_UNIT = 1
    const val INITIAL_DATA_AMOUNT = 5 // in gigabytes
    const val INITIAL_TALK_AMOUNT = 500 // in minutes
    const val INITIAL_TEXT_AMOUNT = 1000 // in texts
    const val DATA_DOLLARS_PER_STEP = 5
    const val DATA_STEP_AMOUNT = 1 // in gigabytes
    const val DATA_MAX_AMOUNT = 20
    const val TALK_DOLLARS_PER_STEP = 1
    const val TALK_STEP_AMOUNT = 25 // in minutes
    const val TALK_MAX_AMOUNT = 2000
    const val TEXT_DOLLARS_PER_STEP = 2
    const val TEXT_STEP_AMOUNT = 50 // in texts
    const val TEXT_MAX_AMOUNT = 2000
    const val INITIAL_BASE_COST = (INITIAL_DATA_AMOUNT / DATA_STEP_AMOUNT).toDouble() * DATA_DOLLARS_PER_STEP +
            (INITIAL_TALK_AMOUNT / TALK_STEP_AMOUNT).toDouble() * TALK_DOLLARS_PER_STEP +
            (INITIAL_TEXT_AMOUNT / TEXT_STEP_AMOUNT).toDouble() * TEXT_DOLLARS_PER_STEP
    const val INITIAL_ADDON_COST = 5.0
    const val INITIAL_USED_DATA = 2
    const val INITIAL_USED_TALK = 125
    const val INITIAL_USED_TEXT = 350
}