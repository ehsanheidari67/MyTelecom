package ir.ipack.ehsan.local.ipack.utils

import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.activities.MainActivity

object PlanConstants {
    val DATA = 1
    val TALK = 2
    val TEXT = 3
    val DATA_UNIT = MainActivity.context?.resources?.getString(R.string.data_units) // gigabytes
    val TALK_UNIT = MainActivity.context?.resources?.getString(R.string.talk_units) // minutes
    val TEXT_UNIT = MainActivity.context?.resources?.getString(R.string.text_units) // texts
    val MIN_UNIT = 1
    val INITIAL_DATA_AMOUNT = 5 // in gigabytes
    val INITIAL_TALK_AMOUNT = 500 // in minutes
    val INITIAL_TEXT_AMOUNT = 1000 // in texts
    val DATA_DOLLARS_PER_STEP = 5
    val DATA_STEP_AMOUNT = 1 // in gigabytes
    val DATA_MAX_AMOUNT = 20
    val TALK_DOLLARS_PER_STEP = 1
    val TALK_STEP_AMOUNT = 25 // in minutes
    val TALK_MAX_AMOUNT = 2000
    val TEXT_DOLLARS_PER_STEP = 2
    val TEXT_STEP_AMOUNT = 50 // in texts
    val TEXT_MAX_AMOUNT = 2000
    val INITIAL_BASE_COST = INITIAL_DATA_AMOUNT / DATA_STEP_AMOUNT * DATA_DOLLARS_PER_STEP +
            INITIAL_TALK_AMOUNT / TALK_STEP_AMOUNT * TALK_DOLLARS_PER_STEP +
            INITIAL_TEXT_AMOUNT / TEXT_STEP_AMOUNT * TEXT_DOLLARS_PER_STEP
    val INITIAL_ADDON_COST = 5
    val INITIAL_USED_DATA = 2
    val INITIAL_USED_TALK = 125
    val INITIAL_USED_TEXT = 350


}