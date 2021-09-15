package by.brust.animalslist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.brust.animalslist.R
import by.brust.animalslist.data.Animal
import by.brust.animalslist.databinding.ItemAnimalBinding


class AnimalAdapter(): RecyclerView.Adapter<AnimalViewHolder>() {

    private var listAnimal = emptyList<Animal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
      holder.bind(listAnimal[position])
    }

    internal fun setAnimals(animals: List<Animal>) {
        this.listAnimal = animals
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listAnimal.size
    }
}