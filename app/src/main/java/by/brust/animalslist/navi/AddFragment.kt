package by.brust.animalslist.navi

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import by.brust.animalslist.R
import by.brust.animalslist.data.Animal
import by.brust.animalslist.data.AnimalViewModel
import by.brust.animalslist.databinding.AddFragmentBinding
import java.util.*
import kotlin.random.Random

class AddFragment: Fragment() {

    private var _binding : AddFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var animalViewModel : AnimalViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddFragmentBinding.inflate(inflater,container,false)
        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            insertDataOnDatabase()
        }
    }

    private fun insertDataOnDatabase() {
        val newAnimalName = binding.nameEditText.text.toString()
        val newAnimalAge = binding.ageEditText.text
        val newAnimalBreed = binding.breedEditText.text.toString()

        if (isInputCorrect(newAnimalName, newAnimalAge, newAnimalBreed)) {
            val animal = Animal(0, newAnimalName, newAnimalAge.toString().toInt(), newAnimalBreed)
            animalViewModel.insert(animal)
            Toast.makeText(requireContext(), " Successfully added animal",Toast.LENGTH_LONG).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_addFragment_to_mainFragment)
        }
        else {
            Toast.makeText(requireContext(), " Incorrect data",Toast.LENGTH_LONG).show()
        }
    }

    private fun isInputCorrect(name: String, age: Editable, breed: String) : Boolean {
        return (age.toString().toInt() >0 && age.toString().toInt() < Int.MAX_VALUE ) &&
                !(TextUtils.isEmpty(name)) &&
                !(TextUtils.isEmpty(breed)) &&
                !(TextUtils.isEmpty(age))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}