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
         * Список встроенных цветов приложения.
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
            Color(239, 154, 154, 255),
            Color(244, 143, 177, 255),
            Color(206, 147, 216, 255),
            Color(179, 157, 219, 255),
            Color(159, 168, 218, 255),
            Color(144, 202, 249, 255),
            Color(129, 212, 250, 255),
            Color(128, 222, 234, 255),
            Color(128, 203, 196, 255),
            Color(165, 214, 167, 255),
            Color(197, 225, 165, 255),
            Color(230, 238, 156, 255),
            Color(255, 245, 157, 255),
            Color(255, 224, 130, 255),
            Color(255, 204, 128, 255),
            Color(255, 171, 145, 255),
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
            Color(229, 57, 53, 255),
            Color(216, 27, 96, 255),
            Color(142, 36, 170, 255),
            Color(94, 53, 177, 255),
            Color(57, 73, 171, 255),
            Color(30, 136, 229, 255),
            Color(3, 155, 229, 255),
            Color(0, 172, 193, 255),
            Color(0, 137, 123, 255),
            Color(67, 160, 71, 255),
            Color(124, 179, 66, 255),
            Color(192, 202, 51, 255),
            Color(253, 216, 53, 255),
            Color(255, 179, 0, 255),
            Color(251, 140, 0, 255),
            Color(244, 81, 30, 255),
            Color(198, 40, 40, 255),
            Color(173, 20, 87, 255),
            Color(106, 27, 154, 255),
            Color(69, 39, 160, 255),
            Color(40, 53, 147, 255),
            Color(21, 101, 192, 255),
            Color(2, 119, 189, 255),
            Color(0, 131, 143, 255),
            Color(0, 105, 92, 255),
            Color(46, 125, 50, 255),
            Color(85, 139, 47, 255),
            Color(158, 157, 36, 255),
            Color(249, 168, 37, 255),
            Color(255, 143, 0, 255),
            Color(239, 108, 0, 255),
            Color(216, 67, 21, 255),
            Color(255, 138, 128, 255),
            Color(255, 128, 171, 255),
            Color(234, 128, 252, 255),
            Color(179, 136, 255, 255),
            Color(140, 158, 255, 255),
            Color(128, 216, 255, 255),
            Color(132, 255, 255, 255),
            Color(167, 255, 235, 255),
            Color(185, 246, 202, 255),
            Color(204, 255, 144, 255),
            Color(244, 255, 129, 255),
            Color(244, 255, 129, 255),
            Color(255, 255, 141, 255),
            Color(255, 229, 127, 255),
            Color(255, 209, 128, 255),
            Color(255, 158, 128, 255),
            Color(255, 82, 82, 255),
            Color(255, 64, 129, 255),
            Color(224, 64, 251, 255),
            Color(124, 77, 255, 255),
            Color(83, 109, 254, 255),
            Color(68, 138, 255, 255),
            Color(64, 196, 255, 255),
            Color(24, 255, 255, 255),
            Color(100, 255, 218, 255),
            Color(105, 240, 174, 255),
            Color(178, 255, 89, 255),
            Color(238, 255, 65, 255),
            Color(255, 255, 0, 255),
            Color(255, 215, 64, 255),
            Color(255, 171, 64, 255),
            Color(255, 110, 64, 255),
            Color(255, 23, 68, 255),
            Color(245, 0, 87, 255),
            Color(213, 0, 249, 255),
            Color(101, 31, 255, 255),
            Color(61, 90, 254, 255),
            Color(41, 121, 255, 255),
            Color(0, 176, 255, 255),
            Color(0, 229, 255, 255),
            Color(29, 233, 182, 255),
            Color(0, 230, 118, 255),
            Color(118, 255, 3, 255),
            Color(198, 255, 0, 255),
            Color(255, 234, 0, 255),
            Color(255, 196, 0, 255),
            Color(255, 145, 0, 255),
            Color(255, 61, 0, 255),
            Color(213, 0, 0, 255),
            Color(197, 17, 98, 255),
            Color(170, 0, 255, 255),
            Color(98, 0, 234, 255),
            Color(48, 79, 254, 255),
            Color(41, 98, 255, 255),
            Color(0, 145, 234, 255),
            Color(0, 184, 212, 255),
            Color(0, 191, 165, 255),
            Color(0, 200, 83, 255),
            Color(100, 221, 23, 255),
            Color(174, 234, 0, 255),
            Color(255, 214, 0, 255),
            Color(255, 171, 0, 255),
            Color(255, 109, 0, 255),
            Color(221, 44, 0, 255),
        )

        /**
         * Список встроенных категорий
         * (существуют локально и не хранятся в базе данных).
         */
        private val _categories = listOf(
            CategoryModel(
                id = 1,
                title = "Здоровье",
                icon = R.drawable.health,
                color = "18439207149134413824",
                builtIn = true
            ),
            CategoryModel(
                id = 2,
                title = "Досуг",
                icon = R.drawable.game,
                color = "18446646380383436800",
                builtIn = true
            ),
            CategoryModel(
                id = 3,
                title = "Дом",
                icon = R.drawable.house,
                color = "18390811552132366336",
                builtIn = true
            ),
            CategoryModel(
                id = 4,
                title = "Работа",
                icon = 2130968634,
                color = "18374876424600289280",
                builtIn = true
            ),
            CategoryModel(
                id = 5,
                title = "Рестораны",
                icon = R.drawable.room_service,
                color = "18446684871880343552",
                builtIn = true
            ),
            CategoryModel(
                id = 6,
                title = "Образование",
                icon = 2130968649,
                color = "18386428121394970624",
                builtIn = true
            ),
            CategoryModel(
                id = 7,
                title = "Подарки",
                icon = 2130968651,
                color = "18441185466905526272",
                builtIn = true
            ),
            CategoryModel(
                id = 8,
                title = "Продукты",
                icon = 2130968679,
                color = "18403602991237038080",
                builtIn = true
            ),
            CategoryModel(
                id = 9,
                title = "Спорт",
                icon = 2130968640,
                color = "18443455563279892480",
                builtIn = true
            ),
            CategoryModel(
                id = 10,
                title = "Транспорт",
                icon = 2130968616,
                color = "18400700649906896896",
                builtIn = true
            ),
            CategoryModel(
                id = 11,
                title = "Счета",
                icon = 2130968627,
                color = "18403602991237038080",
                builtIn = true
            ),
            CategoryModel(
                id = 12,
                title = "Такси",
                icon = 2130968617,
                color = "18446678103011885056",
                builtIn = true
            ),
            CategoryModel(
                id = 13,
                title = "Уход",
                icon = 2130968647,
                color = "18393721529914359808",
                builtIn = true
            )
        )

        /**
         * Самая последняя иконка из списка
         * (используется по умолчанию на экране "Создание категории").
         */
        val Last = getIcons().last()

        /**
         * Возвращает список встроенных цветов.
         */
        fun getColor(): List<Color> { return _colors }

        /**
         * Возвращает список встроенных категорий.
         */
        fun getCategories(): List<CategoryModel> { return _categories }

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

