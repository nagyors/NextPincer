package hf.mobweb.data

import androidx.room.*

@Dao
interface MenuDao {
    @Query("SELECT * FROM Menu")
    fun getAll(): List<Menu>

    @Query("SELECT * FROM Menu " +
            "WHERE restaurantID LIKE :restaurantID")
    fun findMenuForRestaurant(restaurantID: String): List<Menu>

    @Insert
    fun insert(shoppingItems: Menu): Long

    @Update
    fun update(shoppingItem: Menu)

    @Delete
    fun deleteItem(shoppingItem: Menu)
}