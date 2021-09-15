package by.brust.animalslist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Animal::class), version = 1)
abstract class AnimalDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao

    companion object {
        @Volatile
        private var INSTANCE : AnimalDatabase? = null

        @Synchronized
        fun getAnimalDatabase(context: Context) : AnimalDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AnimalDatabase::class.java,
                "animals_db")
                    .build()
            }
                return INSTANCE!!
        }
    }
}