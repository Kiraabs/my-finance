package com.example.myfinance

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Тип категории:
 * - Incomes: доходы
 * - Expanses: расходы
 */
enum class CategoryType { Incomes, Expanses }

/**
 * Модель категории расходов/доходов.
 * Содержит следующие поля:
 * - id: уникальный идентификатор категории
 * - title: название категории
 * - type: тип категории (доходы или расходы)
 * - icon: иконка категории
 * - color: цвет иконки
 */
@Entity(tableName = "category")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var type: String,
    var icon: Int,
) {
    // из-за идиотского Room - это поле должно быть вынесено отдельно
    @Ignore var color: ULong = 0UL
}


