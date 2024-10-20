package com.example.myfinance

import android.content.Context

/**
 * Сохраняет некоторые значения в sharedPrefs, а также позволяет извлекать их оттуда.
 */
class StateManager
{
    companion object
    {
        private fun<T> save(context: Context, key: String, value: T)
        {
            val prefs = context.getSharedPreferences( // создаем sharedPrefs
                "operation_prefs",
                Context.MODE_PRIVATE
            )

            prefs.edit().apply() // сохраняем значение, преобразовывая его в строку
            {
                putString(key, value.toString())
                apply()
            }
        }

        private fun load(context: Context, key: String): String
        {
            if (key.isBlank()) // если ключ был пуст
                return "" // то возвращаем пустую строку

            // возвращаем строку по ключу, если строки с таким ключом не существовало,
            // то возвращаем пустую строку
            return context.getSharedPreferences(
                "operation_prefs",
                Context.MODE_PRIVATE
            ).getString(key, "") ?: ""
        }

        /**
         * Сохраняет строку с указанным ключом и значением.
         * (если ключ и/или строка были пусты - ничего не произойдет)
         * - context: контекст
         * - key: ключ
         * - value: значение
         */
        fun saveStr(context: Context, key: String, value: String)
        {
            if (key.isNotBlank() && value.isNotBlank()) // если ключ и значения не пустые
                save(context, key, value) // то сохраняем
        }

        fun saveLong(context: Context, key: String, value: Long)
        {
            if (key.isNotBlank())
                save(context, key, value)
        }

        fun saveBool(context: Context, key: String, value: Boolean)
        {
            if (key.isNotBlank())
                save(context, key, value)
        }

        /**
         * Безопасно загружает строку из sharedPreferences.
         * (безопасно - значит в результате вызова никогда не будет возвращен null)
         * - context: контекст
         * - key: ключ строки, которую нужно загрузить
         */
        fun loadStr(context: Context, key: String): String
        {
            return load(context, key)
        }

        /**
         * Загружает значение типа Long из sharedPreferences.
         */
        fun loadLong(context: Context, key: String): Long
        {
            val received = load(context, key)
            if (received.isBlank())
                return 0L
            return received.toLong()
        }

        /**
         * Загружает значение типа Long из sharedPreferences.
         */
        fun loadBool(context: Context, key: String): Boolean
        {
            val received = load(context, key)
            if (received.isBlank())
                return false
            return received.toBoolean()
        }
    }
}