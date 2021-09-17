package by.brust.animalslist.data


import android.app.Application
import androidx.lifecycle.*
import androidx.room.RoomDatabase
import by.brust.animalslist.data.cursor.AnimalSQLOpenHelper
import by.brust.animalslist.data.room.AnimalDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimalViewModel(application: Application) : AndroidViewModel(application) {


    val allAnimals : LiveData<List<Animal>>
    private val repository: AnimalRepository


    init {
        val animalDatabase = AnimalDatabase.getAnimalDatabase(application)
        repository = AnimalRepository(animalDatabase,
            AnimalSQLOpenHelper(application.applicationContext))
        allAnimals = repository.allAnimals
    }

    fun insert(animal: Animal) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(animal)
    }

    fun update(animal: Animal) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(animal)
    }
    fun delete(animal: Animal) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(animal)
    }

}