package hf.mobweb.data

import androidx.room.*

@Entity(tableName = "menu")
data class Menu(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "restaurantID") var restaurantID: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "type") var type: Type,
) {
    enum class Type{
        STARTER, SOUP, MAINCOURSE, DESERT, DRINKS;
        companion object {
            @JvmStatic
            @TypeConverter
            fun getByOrdinal(ordinal: Int): Type? {
                var ret: Type? = null
                for (cat in values()) {
                    if (cat.ordinal == ordinal) {
                        ret = cat
                        break
                    }
                }
                return ret
            }

            @JvmStatic
            @TypeConverter
            fun toInt(category: Type): Int {
                return category.ordinal
            }
        }
    }
}