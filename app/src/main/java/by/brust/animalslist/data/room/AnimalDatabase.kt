package by.brust.animalslist.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.brust.animalslist.data.Animal

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