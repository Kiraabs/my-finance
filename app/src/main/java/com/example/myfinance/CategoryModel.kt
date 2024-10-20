package com.example.myfinance

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Модель категории расходов/доходов.
 * Содержит следующие поля:
 * - id: уникальный идентификатор категории
 * - title: название категории
 * - icon: иконка категории
 * - color: цвет иконки
 * - builtIn: является ли категория встроенной в приложение
 * (используется для того, чтобы пользователь не мог удалять встроенные
 * категории. Для удобства имеет значение по умолчанию - false)
 */
@Entity(tableName = "categories")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true) var id: Int = 14,
    var title: String,
    var icon: Int,
    var color: String,
    val builtIn: Boolean = false

    // смещение в id необходимо,
    // потому что встроенные категории имеют id с 1 по 13
)


