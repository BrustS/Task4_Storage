package by.brust.animalslist.data

import androidx.lifecycle.LiveData
import by.brust.animalslist.sortSetting
import kotlinx.coroutines.flow.Flow

class AnimalRepository(private val animalDao: AnimalDao) {

    //retrieving data according to user-selected sorting
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