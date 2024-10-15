package com.example.myfinance.ui.theme

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

// Данный файл содержит общие функции пользовательского интерфейса приложения

/**
 * Переменная, хранящая основной цвет приложения.
 */
val MainColor = Color(38, 166, 154, 255)

/**
 * Функция верхней панели приложения. Содержит следующие параметры:
 * - scrTitle - заголовок текущего экрана
 * - onNavIconClicked - действие, которое должно произойти по щелчку иконки-стрелки
 * (используется, чтобы знать на какой именно экран нужно вернуться)
 * - isMainScr - флаг, означающий является ли текущий экран главным
 * (если да, то иконка возврата на предыдущий экран не отображается.
 * По умолчанию - false, то есть иконка будет отображаться и экран не будет считаться главным)
 */
@Composable
fun TopBar(scrTitle: String, onNavIconClicked: () -> Unit, isMainScr: Boolean = false)
{
    var arrowTint = Color.White // цвет иконки возврата
    if (isMainScr) // если данный экран главный
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
            enabled = !isMainScr
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
            scrTitle,
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

/**
 * Функция выбора цвета заднего фона категории.
 */
@Composable
fun CategoryColorPicker()
{
    // контроллер цвета, хранящий выбранный пользователем цвет
    val con = rememberColorPickerController()

//    Scaffold(
//        topBar =
//        {
//            TopBar(
//                scrTitle = "Добавление цвета",
//                onNavIconClicked = {}
//            )
//        }
//    ) { padg -> // отступ от верхней панели
//        Column(modifier = Modifier.padding(padg))
//        {
//            HsvColorPicker( // палитра выбора цвета
//                modifier = Modifier.fillMaxWidth().height(450.dp).padding(10.dp),
//                controller = con
//            )
//
//            AlphaSlider( // ползунок прозрачности цвета
//                modifier = Modifier.fillMaxWidth().padding(10.dp).height(35.dp),
//                controller = con,
//            )
//
//
//            BrightnessSlider( // ползунок яркости цвета
//                modifier = Modifier.fillMaxWidth().padding(10.dp).height(35.dp),
//                controller = con,
//                wheelColor = Color.Black
//            )
//
//            AlphaTile( // квадрат, отобраюащий выбранный цвет
//                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(6.dp)),
//                controller = con
//            )
//            Text("Selss", color = Color(con.selectedColor.value.value))
//        }
//    }
}