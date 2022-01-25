package hf.mobweb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Restaurant::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao

    companion object {
        fun getDatabase(applicationContext: Context): RestaurantDatabase {
            return Room.databaseBuilder(
                applicationContext,
                RestaurantDatabase::class.java,
                "restaurant"
            ).build();
        }
    }
}