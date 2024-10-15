package com.example.myfinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.ui.theme.CategoryAddingScreen
import com.example.myfinance.ui.theme.CategoryCreatingScreen

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
                startDestination = "category_creating"
            )
            {
                // экран выбора существующих категорий
                composable("category_adding")
                {
                    CategoryAddingScreen(nav)
                }

                // экран добавления категории
                composable("category_creating")
                {
                    CategoryCreatingScreen(nav)
                }
            }
        }
    }
}