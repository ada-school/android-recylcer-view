package org.adaschool.dogslist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.adaschool.dogslist.data.entity.Breed

@Dao
interface BreedDao {

    @Query("SELECT * FROM Breed")
    fun getAll(): LiveData<List<Breed>>

    @Query("SELECT * FROM Breed WHERE uid= :uid")
    fun findById(uid: String): LiveData<Breed>

    @Query("SELECT * FROM Breed WHERE uid= :uid")
    fun findById2(uid: String): Breed

    @Insert
    fun insertAll(vararg breed: Breed)

    @Update
    fun update(breed: Breed)

    @Delete
    fun delete(breed: Breed)
}