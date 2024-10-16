package com.example.myfinance

import androidx.compose.ui.graphics.Color

/**
 * Класс, отвечающий за встроенный контент приложения.
 */
class ContentManager
{
    companion object
    {
        /**
         * Список всех иконок приложения.
         */
        private var _icons: MutableList<Int>? = null

        /**
         * Список готовых цветов приложения.
         */
        private val _colors = listOf(
            Color.Red,
            Color.Blue,
            Color.Green,
            Color.Cyan,
            Color.DarkGray,
            Color.Yellow,
            Color.Magenta,
            Color.Gray,
            Color(239, 83, 80, 255),
            Color(236, 64, 122, 255),
            Color(171, 71, 188, 255),
            Color(126, 87, 194, 255),
            Color(92, 107, 192, 255),
            Color(66, 165, 245, 255),
            Color(41, 182, 246, 255),
            Color(38, 198, 218, 255),
            Color(38, 166, 154, 255),
            Color(102, 187, 106, 255),
            Color(156, 204, 101, 255),
            Color(212, 225, 87, 255),
            Color(255, 238, 88, 255),
            Color(255, 202, 40, 255),
            Color(255, 167, 38, 255),
            Color(255, 112, 67, 255),
        )

        /**
         * Самая последняя иконка из списка
         * (используется по умолчанию на экране "Создание категории").
         */
        val Last = getIcons().last()

        /**
         * Возвращает список готовых цветов.
         */
        fun getColor(): List<Color> { return _colors }

        /**
         * Инициализирует и загружает список всех иконок из Drawable.
         */
        fun getIcons(): List<Int>
        {
            // список инициализируется единожды, то есть загружаются иконки категорий,
            // при первом обращении к данной функции,
            // после, список просто возвращается
            if (_icons == null)
            {
                 _icons = mutableListOf()
                 for (i in R.drawable::class.java.fields) // перебираем все иконки в Drawable
                     if (!i.name.contains("ic_launcher")) // пропускаем системные иконки
                         _icons!!.add(i.getInt(null))
            }
            return _icons!!
        }
    }
}

