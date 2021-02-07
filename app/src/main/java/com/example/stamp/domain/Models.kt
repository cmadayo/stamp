package com.example.stamp.domain

import android.graphics.Bitmap
import com.example.stamp.database.StampEntity

data class DomainStamp(
    val id: Int,
    val title: String,
    val image: Bitmap,
  ) {
    fun asDatabaseEntity() : StampEntity {
        return StampEntity(this.id, this.title, this.image)
    }
}


