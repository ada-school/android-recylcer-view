package org.adaschool.dogslist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.adaschool.dogslist.data.dao.BreedDao
import org.adaschool.dogslist.data.entity.Breed
import org.adaschool.dogslist.service.DogsImageService
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    private val breedDao: BreedDao,
    private val dogsImageService: DogsImageService
) : ViewModel() {


    val liveDataDogsList = breedDao.getAll()


    var selectedBreed: Breed? = null


    fun loadDogBreeds() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = dogsImageService.getDogBreedsList()
            if (response.isSuccessful) {
                val dogListDto = response.body()
                dogListDto?.message?.map { breedMap ->
                    getBreedImage(breedMap.key, breedMap.value)
                }
            }
        }
    }

    private fun getBreedImage(breed: String, variations: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = dogsImageService.getBreedRandomImage(breed)
            if (response.isSuccessful) {
                val dogImageUrl = response.body()?.message
                val dogBreed = Breed(0, breed, dogImageUrl, variations)
                breedDao.insertAll(dogBreed)
            }
        }
    }


}





