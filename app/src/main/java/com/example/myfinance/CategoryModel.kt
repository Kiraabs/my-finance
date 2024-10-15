package com.example.myfinance

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var type: String,
    var icon: Int,
) {
    @Ignore var color: ULong = 0UL
}
