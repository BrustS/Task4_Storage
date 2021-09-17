package by.brust.animalslist.data

import androidx.lifecycle.LiveData

interface IAnimalDao {
    fun getAllAnimalsByName() : LiveData<List<Animal>>
    fun getAllAnimalsByAge() : LiveData<List<Animal>>
    fun getAllAnimalsByBreed() : LiveData<List<Animal>>
    suspend fun insertAnimal(animal: Animal)
    suspend fun updateAnimal(animal : Animal)
    suspend fun deleteAnimal(animal : Animal)
}