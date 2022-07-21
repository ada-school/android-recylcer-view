package org.adaschool.dogslist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import org.adaschool.dogslist.R
import org.adaschool.dogslist.data.entity.Breed
import org.adaschool.dogslist.databinding.FragmentDogListBinding
import org.adaschool.dogslist.ui.viewmodel.MainActivityViewModel

@AndroidEntryPoint
class DogsListFragment : Fragment() {

    private var _binding: FragmentDogListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDogListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigateToDetailsFragment.setOnClickListener {
            updateBreedInfoAndNavigateToDetailsFragment()
        }
    }

    private fun updateBreedInfoAndNavigateToDetailsFragment() {
        viewModel.selectedBreed = Breed(0, "German Sheppard", null, emptyList())
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, DogBreedDetailsFragment())
            .addToBackStack("Details").commit()
    }


}