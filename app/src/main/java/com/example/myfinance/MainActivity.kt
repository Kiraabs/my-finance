package com.example.myfinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.ui.theme.CategoryChoosingScreen
import com.example.myfinance.ui.theme.CategoryBuilderScreen
import com.example.myfinance.ui.theme.MainScreen
import com.example.myfinance.ui.theme.OperationAddingScreen

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContent()
        {
            val nav = rememberNavController() // контроллер для навигации между экранами
            val dao = FinanceDB.instance(this).financeDao()

            NavHost( // описание существующих экранов
                navController = nav,
                startDestination = "main_screen"
            )
            {
                composable("main_screen") // главный экран
                {
                    MainScreen(nav)
                }

                composable("operation_adding") // экран добавления операции
                {
                    OperationAddingScreen(nav, dao) {}
                }

                composable("category_choosing") // экран выбора существующих категорий
                {
                    CategoryChoosingScreen(nav, dao)
                }

                composable("category_builder") // экран создания категории
                {
                    CategoryBuilderScreen(
                        dao = dao,
                        onExited =
                        {
                            nav.navigate("category_choosing") {
                                popUpTo("category_choosing") {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}