package by.brust.animalslist.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import by.brust.animalslist.data.Animal
import by.brust.animalslist.data.IAnimalDao

@Dao
interface AnimalDao : IAnimalDao {
    //Getting animal data sorted by name
    @Query("SELECT * FROM animal_table ORDER BY name ASC")
    override fun getAllAnimalsByName() : LiveData<List<Animal>>

    //Getting animal data sorted by age
    @Query("SELECT * FROM animal_table ORDER BY age ASC")
    override fun getAllAnimalsByAge() : LiveData<List<Animal>>

    //Getting animal data sorted by breed
    @Query("SELECT * FROM animal_table ORDER BY breed ASC")
    override fun getAllAnimalsByBreed() : LiveData<List<Animal>>

    //Insert animal from database
    @Insert
    override suspend fun insertAnimal(animal: Animal)

    //Update animal from database
    @Update
    override suspend fun updateAnimal(animal : Animal)


    //Delete animal from database
    @Delete
    override suspend fun deleteAnimal(animal : Animal)
}