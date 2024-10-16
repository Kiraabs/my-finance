package com.example.myfinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.ui.theme.CategoryAddingScreen
import com.example.myfinance.ui.theme.CategoryBuilderScreen

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContent()
        {
            // контроллер для навигации между экранами
            val nav = rememberNavController()

            NavHost( // описание существующих экранов
                navController = nav,
                startDestination = "category_builder"
            )
            {
                // экран выбора существующих категорий
                composable("category_adding")
                {
                    CategoryAddingScreen(nav)
                }

                // экран добавления категории
                composable("category_builder")
                {
                    CategoryBuilderScreen(nav) {}
                }
            }
        }
    }
}