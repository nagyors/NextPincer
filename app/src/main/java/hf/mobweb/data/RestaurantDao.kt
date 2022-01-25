package hf.mobweb.data

import androidx.room.*

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    fun getAll(): List<Restaurant>

    @Query("SELECT * FROM restaurant " +
            "WHERE id LIKE :restaurantID")
    fun getRestaurant(restaurantID: Long?): Restaurant

    @Insert
    fun insert(restaurant: Restaurant): Long

    @Update
    fun update(restaurant: Restaurant)

    @Delete
    fun deleteItem(restaurant: Restaurant)
}