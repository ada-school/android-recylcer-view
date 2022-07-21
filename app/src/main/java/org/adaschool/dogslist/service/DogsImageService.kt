package org.adaschool.dogslist.service

import org.adaschool.dogslist.service.dto.DogsListDto
import org.adaschool.dogslist.service.dto.RandomDogImageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsImageService {

    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<RandomDogImageDto>

    @GET("breed/{breed}/images/random")
    suspend fun getBreedRandomImage(@Path("breed") breed: String): Response<RandomDogImageDto>

    @GET("breeds/list/all")
    suspend fun getDogBreedsList(): Response<DogsListDto>

}