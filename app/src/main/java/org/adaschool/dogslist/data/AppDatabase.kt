package org.adaschool.dogslist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.adaschool.dogslist.data.dao.BreedDao
import org.adaschool.dogslist.data.entity.Breed

@Database(entities = [Breed::class], version = 1)
@TypeConverters(CustomConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun breedDao(): BreedDao
}