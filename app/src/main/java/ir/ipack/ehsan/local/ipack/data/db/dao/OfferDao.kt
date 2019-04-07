package ir.ipack.ehsan.local.ipack.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import ir.ipack.ehsan.local.ipack.data.db.entity.OfferEntity

@Dao
interface OfferDao {
    @Query("select * from offer")
    fun getAll(): List<OfferEntity>
}