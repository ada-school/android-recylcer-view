package org.adaschool.dogslist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.adaschool.dogslist.databinding.FragnentDogBreedDetailsBinding
import org.adaschool.dogslist.ui.viewmodel.MainActivityViewModel

@AndroidEntryPoint
class DogBreedDetailsFragment : Fragment() {

    private var _binding: FragnentDogBreedDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragnentDogBreedDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.selectedBreed != null) {
            binding.breedName.text = viewModel.selectedBreed?.name
        }
    }

}