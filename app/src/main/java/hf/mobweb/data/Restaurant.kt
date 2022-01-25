package hf.mobweb.data

import androidx.room.*

@Entity(tableName = "restaurant")
data class Restaurant(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "rating") var rating: Float,
    @ColumnInfo(name = "pricing") var pricing: String,
    @ColumnInfo(name = "cuisine") var cuisine: String
)