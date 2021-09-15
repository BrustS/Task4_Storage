package by.brust.animalslist.data


import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AnimalViewModel(application: Application) : AndroidViewModel(application) {


    val allAnimals : LiveData<List<Animal>>
    private val repository: AnimalRepository

    init {
        val animalDao = AnimalDatabase.getAnimalDatabase(application).animalDao()
        repository = AnimalRepository(animalDao)
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