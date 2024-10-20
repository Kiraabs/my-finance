package com.example.myfinance

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operations")
data class OperationModel(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var categoryId: Int,
    var sum: Int,
    var date: Long,
    var type: String
)

enum class OperationType { Incomes, Expenses }
