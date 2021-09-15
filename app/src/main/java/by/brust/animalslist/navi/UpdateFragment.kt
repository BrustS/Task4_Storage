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
import androidx.navigation.fragment.navArgs
import by.brust.animalslist.R
import by.brust.animalslist.data.Animal
import by.brust.animalslist.data.AnimalViewModel
import by.brust.animalslist.databinding.AddFragmentBinding
import by.brust.animalslist.databinding.UpdateFragmentBinding

class UpdateFragment : Fragment() {

    //Binding in fragment
    private var _binding : UpdateFragmentBinding? = null
    private val binding get() = _binding!!

    //Variable declaration ViewModel
    private lateinit var animalViewModel : AnimalViewModel

    // Save Args
    private val args by navArgs<UpdateFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialization variable
        _binding = UpdateFragmentBinding.inflate(inflater,container,false)
        binding.nameEditTextUpdate.setText(args.currentAnimal.name)
        binding.ageEditTextUpdate.setText(args.currentAnimal.age.toString())
        binding.breedEditTextUpdate.setText(args.currentAnimal.breed)
        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)

        //Click button "Update Info"
        binding.updateButton.setOnClickListener {
            updateAnimalInfo()
        }

        //Click image button "Delete"
        binding.deleteButton.setOnClickListener {
            animalViewModel.delete(args.currentAnimal)
            Toast.makeText(requireContext(), " Successfully deleted info", Toast.LENGTH_LONG).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_updateFragment_to_mainFragment)
        }
        return binding.root
    }

    // Update information about animal and show Toast
    private fun updateAnimalInfo() {
        val name = binding.nameEditTextUpdate.text.toString()
        val age = binding.ageEditTextUpdate.text
        val breed = binding.breedEditTextUpdate.text.toString()

        if (isInputCorrect(name, age, breed)) {
            val updatedAnimal = Animal(args.currentAnimal.id, name, age.toString().toInt(), breed)
            animalViewModel.update(updatedAnimal)
            Toast.makeText(requireContext(), " Successfully updated info about animal", Toast.LENGTH_LONG).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_updateFragment_to_mainFragment)
        }
        else {
            Toast.makeText(requireContext(), " Incorrect data", Toast.LENGTH_LONG).show()
        }
    }

    //Check the data entered by the user
    private fun isInputCorrect(name: String, age: Editable, breed: String) : Boolean {
        return (age.toString().toInt() >0 && age.toString().toInt() < Int.MAX_VALUE ) &&
                !(TextUtils.isEmpty(name)) &&
                !(TextUtils.isEmpty(breed)) &&
                !(TextUtils.isEmpty(age))
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}