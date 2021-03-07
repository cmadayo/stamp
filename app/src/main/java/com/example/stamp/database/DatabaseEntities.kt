package com.example.stamp.database

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stamp.businesslogic.domains.DomainStamp

@Entity
data class StampEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val image: Bitmap,
)

/**
 * Map
 */
fun List<StampEntity>.asDomainModel(): List<DomainStamp> {
    return map {
        DomainStamp(
            id = it.id,
            title = it.title,
            image = it.image,
        )
    }
}