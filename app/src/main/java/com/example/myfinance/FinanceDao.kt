package com.example.myfinance

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Содержит запросы к базе данных.
 */
@Dao
interface FinanceDao
{
    /**
     * Возвращает список всех категорий из базы данных.
     */
    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<CategoryModel>

    /**
     * Выполняет вставку категории в базу данных.
     */
    @Insert
    suspend fun insert(categoryModel: CategoryModel)

    /**
     * Удаляет категорию в базу данных.
     */
    @Delete
    suspend fun delete(categoryModel: CategoryModel)
}