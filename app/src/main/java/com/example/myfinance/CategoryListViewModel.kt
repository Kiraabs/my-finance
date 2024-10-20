package com.example.myfinance

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * Представляет автоматически обновляющийся список категорий.
 */
class CategoryListViewModel : ViewModel()
{
    companion object
    {
        /**
         * Выбранная пользователем категория.
         */
        var SelectedCategory: CategoryModel? = null
    }

    /**
     * Список категорий.
     */
    private val _categories = mutableStateOf(listOf<CategoryModel>())

    /**
     * Состояние списка категорий.
     */
    val categoryList: State<List<CategoryModel>> = _categories

    // копирование категорий из встроенного контента приложения, при инициализации списка
    init
    {
        copyFrom(ContentManager.getCategories())
    }

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
     * Выполняет замену старой категории на новую по id.
     */
    fun replace(id: Int, categoryModel: CategoryModel)
    {
        remove(_categories.value.find { it.id == id}!!) // удаляем старую категорию из списка по id
        categoryModel.id = id // присваиваем id старой категории новой
        add(categoryModel) // добавляем новую категорию в список
    }

    /**
     * Скопировать данные из списка (базы данных или встроенного контента)
     */
    fun copyFrom(list: List<CategoryModel>)
    {
        list.forEach { _categories.value += it }
    }
}