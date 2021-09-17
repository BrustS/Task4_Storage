package by.brust.animalslist.data.cursor

import android.content.ContentValues
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import by.brust.animalslist.COLUMN_AGE
import by.brust.animalslist.COLUMN_BREED
import by.brust.animalslist.COLUMN_NAME
import by.brust.animalslist.TABLE_NAME
import by.brust.animalslist.data.Animal
import by.brust.animalslist.data.IAnimalDao

class AnimalOpenHelperDao(db: AnimalSQLOpenHelper) : IAnimalDao{
    private val readable = db.readableDatabase
    private val  writable = db.writableDatabase
    private val resultAnimal = MutableLiveData(0)

    override fun getAllAnimalsByName(): LiveData<List<Animal>> {
       return getAnimals("name")
    }

    override fun getAllAnimalsByAge(): LiveData<List<Animal>> {
        return getAnimals("age")
    }

    override fun getAllAnimalsByBreed(): LiveData<List<Animal>> {
        return getAnimals("breed")
    }

    override suspend fun insertAnimal(animal: Animal) {
        val values = ContentValues().apply {
            put(COLUMN_NAME, animal.name)
            put(COLUMN_AGE, animal.age)
            put(COLUMN_BREED,animal.breed)
        }
        writable.insert(TABLE_NAME, null, values)
        resultAnimal.postValue(0)
    }

    override suspend fun updateAnimal(animal: Animal) {
        val values = ContentValues().apply {
            put(COLUMN_NAME, animal.name)
            put(COLUMN_AGE, animal.age)
            put(COLUMN_BREED,animal.breed)
        }
        val selection = "id = ?"
        val selectionArgs = arrayOf(animal.id.toString())
        writable.update(TABLE_NAME,values,selection,selectionArgs)
        resultAnimal.postValue(0)
    }

    override suspend fun deleteAnimal(animal: Animal) {
        val selection = "id = ?"
        val selectionArgs = arrayOf(animal.id.toString())
        writable.delete(TABLE_NAME,selection,selectionArgs)
        resultAnimal.postValue(0)
    }

    fun getAnimals(sort : String) : LiveData<List<Animal>> {

        val animalsList = mutableListOf<Animal>()
        val cursor = readable.rawQuery(
            "SELECT * FROM animal_table ORDER BY $sort ASC",
            null).use { cursor ->
           try {
            if(cursor.moveToFirst()) {
                do {
                    val animalName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                    val animalAge = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE))
                    val animalBreed = cursor.getString(cursor.getColumnIndex(COLUMN_BREED))
                    animalsList.add(Animal(0, animalName, animalAge, animalBreed))
                } while (cursor.moveToFirst())

            }
           } catch (e:Exception) {
           } finally {
               cursor.close()
           }
        }
        return resultAnimal.map { animalsList }
    }
}