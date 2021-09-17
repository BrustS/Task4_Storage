package by.brust.animalslist.data

import androidx.lifecycle.LiveData
import by.brust.animalslist.data.cursor.AnimalOpenHelperDao
import by.brust.animalslist.data.cursor.AnimalSQLOpenHelper
import by.brust.animalslist.data.room.AnimalDao
import by.brust.animalslist.data.room.AnimalDatabase
import by.brust.animalslist.isUseRoom
import by.brust.animalslist.sortSetting

class AnimalRepository (private val roomDatabase :AnimalDatabase,
private val openHelperDatabase: AnimalSQLOpenHelper) {

    private val animalDao = if (isUseRoom) roomDatabase.animalDao()
    else openHelperDatabase.openHelperDao
    val allAnimals: LiveData<List<Animal>> = when (sortSetting) {
        "name" -> animalDao.getAllAnimalsByName()
        "age" -> animalDao.getAllAnimalsByAge()
        else -> animalDao.getAllAnimalsByBreed()
    }

   suspend fun insert(animal: Animal) {
        animalDao.insertAnimal(animal)
    }

    suspend fun update(animal: Animal) {
        animalDao.updateAnimal(animal)
    }
    suspend fun delete(animal: Animal) {
        animalDao.deleteAnimal(animal)
    }
}