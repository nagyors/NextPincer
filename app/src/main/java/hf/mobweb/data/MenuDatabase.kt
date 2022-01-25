package hf.mobweb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Menu::class], version = 1)
@TypeConverters(value = [Menu.Type::class])
abstract class MenuDatabase: RoomDatabase() {
    abstract fun menuDao(): MenuDao

    companion object {
        fun getDatabase(applicationContext: Context): MenuDatabase {
            return Room.databaseBuilder(
                applicationContext,
                MenuDatabase::class.java,
                "menu"
            ).build();
        }
    }
}