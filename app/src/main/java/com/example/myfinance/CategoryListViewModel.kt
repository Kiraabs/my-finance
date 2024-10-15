package com.example.myfinance

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * Представляет автоматически обновляющийся список категорий
 */
class CategoryListViewModel : ViewModel()
{
    /**
     * Список категорий.
     */
    private val _categories = mutableStateOf(listOf<CategoryModel>())

    /**
     * Состояние списка категорий.
     */
    val categoryModelList: State<List<CategoryModel>> = _categories

    /**
     * Добавить категорию в список.
     */
    fun add(categoryModel: CategoryModel)
    {
        _categories.value += categoryModel
    }

    /**
     * Удалить категорию из списка.
     */
    fun remove(categoryModel: CategoryModel)
    {
        _categories.value -= categoryModel
    }

    /**
     * Скопировать данные из списка (список должен быть получен из базы данных)
     */
    fun copyFrom(list: List<CategoryModel>)
    {
        list.forEach { _categories.value += it }
    }
}