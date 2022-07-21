package org.adaschool.dogslist.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import org.adaschool.dogslist.R
import org.adaschool.dogslist.databinding.ActivityMainBinding
import org.adaschool.dogslist.ui.fragment.DogsListFragment
import org.adaschool.dogslist.ui.viewmodel.MainActivityViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainActivityViewModel

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.loadDogBreeds()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, DogsListFragment())
                .commit()
        }


    }


}

