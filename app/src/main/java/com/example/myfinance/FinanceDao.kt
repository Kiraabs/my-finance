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
    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<CategoryModel>

    /**
     * Возвращает список всех операций из базы данных.
     */
    @Query("SELECT * FROM operations")
    suspend fun getOperations(): List<OperationModel>

    /**
     * Выполняет вставку операции в базу данных.
     */
    @Insert
    suspend fun insertOperation(operation: OperationModel)

    /**
     * Выполняет удаление категории из базы данных.
     */
    @Delete
    suspend fun deleteOperation(operation: OperationModel)

    /**
     * Выполняет вставку категории в базу данных.
     */
    @Insert
    suspend fun insertCategory(category: CategoryModel)

    /**
     * Удаляет категорию в базу данных.
     */
    @Delete
    suspend fun deleteCategory(category: CategoryModel)
}