package ir.ipack.ehsan.local.ipack.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "offer")
class OfferEntity(
    @PrimaryKey @ColumnInfo(name = "offer_id") val offerId: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "body") val body: String?,
    @ColumnInfo(name = "terms_conditions") val termsConditions: String?,
    @ColumnInfo(name = "card_icon") val cardIcon: Int?,
    @ColumnInfo(name = "cost") val cost: Double?,
    @ColumnInfo(name = "type") val type: Int?,
    @ColumnInfo(name = "icon") val icon: String?,
    @ColumnInfo(name = "is_app_offer") val isAppOffer: Boolean?,
    @ColumnInfo(name = "is_unlimited") val isUnlimited: Boolean?,
    @ColumnInfo(name = "affects_base_cost") val affectsBaseCost: Boolean?,
    @ColumnInfo(name = "is_share_card") val isShareCard: Boolean?,
    @ColumnInfo(name = "amount_added_to_cycle") val amountAddedToCycle: Int?,
    @ColumnInfo(name = "app_name") val appName: String?,
    @ColumnInfo(name = "usage") val usage: Float?
)