package by.brust.animalslist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {
    //Getting animal data sorted by name
    @Query("SELECT * FROM animal_table ORDER BY name ASC")
    fun getAllAnimalsByName() : LiveData<List<Animal>>

    //Getting animal data sorted by age
    @Query("SELECT * FROM animal_table ORDER BY age ASC")
    fun getAllAnimalsByAge() : LiveData<List<Animal>>

    //Getting animal data sorted by breed
    @Query("SELECT * FROM animal_table ORDER BY breed ASC")
    fun getAllAnimalsByBreed() : LiveData<List<Animal>>

    //Insert animal from database
    @Insert
    suspend fun insertAnimal(animal: Animal)

    //Update animal from database
    @Update
    suspend fun updateAnimal(animal : Animal)


    //Delete animal from database
    @Delete
    suspend fun deleteAnimal(animal : Animal)
}