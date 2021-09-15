package by.brust.animalslist.recycler

import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import by.brust.animalslist.R
import by.brust.animalslist.data.Animal
import by.brust.animalslist.databinding.ItemAnimalBinding
import by.brust.animalslist.navi.MainFragmentDirections

class AnimalViewHolder(private val binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(animal: Animal) {
        binding.apply {
            nameTextView.text = animal.name
            ageTextView.text = animal.age.toString()
            breedTextView.text = animal.breed

            rowLayout.setOnClickListener{
                val action = MainFragmentDirections.actionMainFragmentToUpdateFragment(animal)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }


    }

}