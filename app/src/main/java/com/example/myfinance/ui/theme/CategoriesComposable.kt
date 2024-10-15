package com.example.myfinance.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

// Данный файл содержит все функции пользовательского интерфейса,
// связанные с категориями

/**
 * Экран существующих категорий.
 * - nav: контроллер для перехода к другим экранам
 *  или возврата на главный
 */
@Composable
fun CategoryAddingScreen(nav: NavController)
{

}

/**
 * Экран создания категории.
 * - nav: контроллер для перехода к другим экранам
 * или возврата на главный
 */
@Composable
fun CategoryCreatingScreen(nav: NavController)
{
    Scaffold( // разметка данного экрана
        topBar =
        {
            TopBar(
                "Создание категории",
                onNavIconClicked = { // возврат к пред. экрану
                    nav.navigate("category_adding")
                    {
                        popUpTo("category_adding")
                        {
                            inclusive = true
                        }
                    }
                },
            )
        }
    )
    { padding ->

        Column(
            modifier = Modifier.padding(padding)
        )
        {

        }
    }
}