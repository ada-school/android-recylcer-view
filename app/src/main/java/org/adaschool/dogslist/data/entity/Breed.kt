package org.adaschool.dogslist.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Breed(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var name: String,
    var imageUrl: String?,
    var variations: List<String>
)