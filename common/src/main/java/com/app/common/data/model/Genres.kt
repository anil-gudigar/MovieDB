package com.app.common.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genres")
class Genres(
    @field:SerializedName("name")
    var name: String? = null,
    @PrimaryKey
    @field:SerializedName("id")
    val id: String
) {
    override fun toString(): String {
        return "[name = $name, id = $id]"
    }
}

