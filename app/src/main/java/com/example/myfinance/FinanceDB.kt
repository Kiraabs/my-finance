package com.example.myfinance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * База данных приложения.
 */
@Database(entities = [CategoryModel::class, OperationModel::class], version = 1)
abstract class FinanceDB : RoomDatabase()
{
    /**
     * Экземпляр интерфейса базы данных.
     */
    abstract fun financeDao(): FinanceDao

    companion object
    {
        /**
         * Экземпляр самой базы данных.
         */
        @Volatile
        private var _instance: FinanceDB? = null

        /**
         * Возвращает экземпляр базы данных.
         */
        fun instance(context: Context): FinanceDB
        {
            return _instance ?: synchronized(this)
            {
                val inst = Room.databaseBuilder(
                    context.applicationContext,
                    FinanceDB::class.java,
                    "finance_db"
                ).build()
                _instance = inst
                inst
            }
        }
    }
}