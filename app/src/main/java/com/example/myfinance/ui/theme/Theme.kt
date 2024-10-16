package com.example.myfinance.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Данный файл содержит общие функции пользовательского интерфейса приложения.

/**
 * Переменная, хранящая основной цвет приложения.
 */
val MainColor = Color(38, 166, 154, 255)

/**
 * Функция верхней панели приложения. Содержит следующие параметры:
 * - screenTitle - заголовок текущего экрана
 * - onNavIconClicked - действие, которое должно произойти по щелчку иконки-стрелки
 * (используется, чтобы знать на какой именно экран нужно вернуться)
 * - isMainScreen - флаг, означающий является ли текущий экран главным
 * (если да, то иконка возврата на предыдущий экран не отображается.
 * По умолчанию - false, то есть иконка будет отображаться и экран не будет считаться главным)
 */
@Composable
fun TopBar(screenTitle: String, onNavIconClicked: () -> Unit, isMainScreen: Boolean = false)
{
    var arrowTint = Color.White // цвет иконки возврата
    if (isMainScreen) // если данный экран главный
        arrowTint = Color.Transparent // то делаем ее прозрачной

    // контейнер верхней панели приложения
    // встроенная функция CenterAlignedTopAppBar не используется,
    // потому что я не нашел как скруглить ее края
    Row(
        modifier = Modifier
            .background(
                color = MainColor,
                shape = RoundedCornerShape(
                    bottomEndPercent = 30,
                    bottomStartPercent = 30
                )
            )
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(vertical = 32.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        IconButton( // иконка возврата на предыдущий экран
            onClick = onNavIconClicked,
            enabled = !isMainScreen
            // данная иконка активна только тогда,
            // когда текущий экран не является главным
        )
        {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "PrevScr",
                tint = arrowTint
            )
        }

        Text( // заголовок панели
            screenTitle,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )

        IconButton( // иконка вызова главного меню
            onClick = onNavIconClicked
        )
        {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Default.Menu,
                contentDescription = "MainMenu",
                tint = Color.White
            )
        }
    }
}

@Composable
fun BottomBar(onActionButtonClicked: () -> Unit, actionButtonIcon: ImageVector)
{
    Row( // контейнер нижней панели
        modifier = Modifier
            .background(
                color = MainColor,
                shape = RoundedCornerShape(
                    topEndPercent = 30,
                    topStartPercent = 30
                )
            )
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(vertical = 18.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        FloatingActionButton(
            onClick = onActionButtonClicked,
            containerColor = Color.White,
            contentColor = MainColor,
            shape = CircleShape
        )
        {
            Icon(
                imageVector = actionButtonIcon,
                contentDescription = actionButtonIcon.name,
            )
        }
    }
}

/**
 * Функция, возвращающая оформление цветов для полей ввода приложения.
 */
@Composable
fun getDefaultTextFieldColors(): TextFieldColors
{
    return TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        errorSupportingTextColor = Color.Red,
        focusedIndicatorColor = MainColor,
        cursorColor = MainColor,
        errorIndicatorColor = Color.Red,
    )
}