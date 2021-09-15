package by.brust.animalslist.navi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import by.brust.animalslist.R
import by.brust.animalslist.data.*
import by.brust.animalslist.databinding.MainFragmentBinding
import by.brust.animalslist.recycler.AnimalAdapter

class MainFragment: Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var animalViewModel: AnimalViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        val animalAdapter = AnimalAdapter()
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = animalAdapter
        }

        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)
        animalViewModel.allAnimals.observe(viewLifecycleOwner, Observer { animal ->
            animalAdapter.setAnimals(animal)
        })


        binding.fabAdd.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_addFragment)
        }

         return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filterButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_sortFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    }
