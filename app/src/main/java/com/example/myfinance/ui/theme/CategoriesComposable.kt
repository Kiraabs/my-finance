package com.example.myfinance.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myfinance.ContentManager
import com.example.myfinance.CategoryModel
import com.example.myfinance.CategoryType
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

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
 * - onBuilt: возвращает созданную категорию
 */
@Composable
fun CategoryBuilderScreen(nav: NavController, onBuilt: (CategoryModel) -> Unit)
{
    val scope = rememberCoroutineScope() // корутина для параллельных операций

    // название категории
    var categoryName by remember { mutableStateOf("") }

    // тип категории (по умолчанию - incomes)
    var categoryType by remember { mutableStateOf(CategoryType.Incomes) }

    // сама иконка категории (по умолчанию - первая иконка из Drawable)
    var categoryIcon by remember { mutableIntStateOf(ContentManager.Last) }

    // цвет заднего фона иконки категории (по умолчанию - основной цвет приложения)
    var categoryColor by remember { mutableStateOf(MainColor.value) }

    // флаг показа окна выбора цвета
    var showColorPicker by remember { mutableStateOf(false) }

    // флаг показа окна выбора иконки
    var showIconPicker by remember { mutableStateOf(false) }

    // флаг для переключателей типа категории
    var checked by remember { mutableStateOf(true) }

    // флаг: были ли ошибки
    var hasErrors by remember { mutableStateOf(false) }

    // если пользователь нажал на кнопку "Выбрать цвет"
    // то показать окно выбора цвета
    if (showColorPicker)
    {
        ColorPickerDialog(
            onColorPicked = { categoryColor = it }, // вернуть цвет и закрыть окно
            onExited = { showColorPicker = false } // скрыть окно
        )
    }

    // если пользователь нажал на кнопку "Выбрать иконку"
    // то показать окно выбора иконки
    if (showIconPicker)
    {
        IconPickerDialog(
            onIconPicked = { categoryIcon = it }, // вернуть иконку и закрыть окно
            onExited = { showIconPicker = false } // скрыть окно
        )
    }

    Scaffold( // разметка данного экрана
        topBar = // верхняя панель экрана
        {
            TopBar(
                screenTitle = "Создание категории",
                onNavIconClicked = // возврат к пред. экрану
                {
                    nav.navigate("category_adding") {
                        popUpTo("category_adding") {
                            inclusive = true
                        }
                    }
                },
            )
        },
        bottomBar = // нижняя панель экрана
        {
            BottomBar(
                onActionButtonClicked = // кнопка "Галочка"
                {
                    if (categoryName.isBlank()) // если не введено название
                        hasErrors = true // ошибки были
                    else
                    {
                        val newCategory = CategoryModel( // создаем категорию, как програмнный объект
                            title =  categoryName,
                            type = categoryType.toString(),
                            icon = categoryIcon,
                        )
                        newCategory.color = categoryColor
                        onBuilt(newCategory) // возвращаем ее
                    }
                },
                actionButtonIcon = Icons.Default.Check
            )
        }
    )
    { padding ->
        Column( // контентейнер, содержащий все компоненты экрана
            modifier = Modifier
                .padding(padding)
                .padding(vertical = 50.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            val charLimit = 40 // максимальная длина названия
            TextField( // поле ввода названия категории
                modifier = Modifier.fillMaxWidth(0.8f),
                singleLine = true,
                value = categoryName,
                onValueChange =
                {
                    categoryName = it.take(charLimit)

                    // если ранее, были ошибки и теперь поле ввода не пустое,
                    // то скрываем ошибки
                    if (hasErrors && categoryName.isNotBlank())
                        hasErrors = false
                },
                placeholder =
                {
                    Text(
                        "Название",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black.copy(0.3f),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                colors = getDefaultTextFieldColors(),
                textStyle = MaterialTheme.typography.titleMedium,
                isError = hasErrors,
                supportingText =
                {
                    if (hasErrors) // отображаем ошибку, если она была
                    {
                        Text(
                            "Введите название",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            )
            Text( // выводим сколько символов введено и сколько еще можно ввести
                text = "${categoryName.length}/${charLimit}",
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Row( // строка переключателей типа категории
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                // переключатели типа категории
                RadioButton(
                    selected = checked,
                    onClick =
                    {
                        checked = true
                        categoryType = CategoryType.Incomes
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MainColor
                    )
                )
                Text("Расходы", style = MaterialTheme.typography.bodyLarge)
                RadioButton(
                    selected = ! checked,
                    onClick =
                    {
                        checked = false
                        categoryType = CategoryType.Expanses
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MainColor
                    )
                )
                Text("Доходы", style = MaterialTheme.typography.bodyLarge)
            }
            Button( // кнопка перехода на экран выбора цвета
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 25.dp)
                    .border(
                        width = 2.dp,
                        color = MainColor,
                        shape = RoundedCornerShape(20)
                    ),
                onClick = { showColorPicker = true }, // вызов окна выбора цвета
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MainColor
                )
            )
            {
               Text(
                   "Выбрать цвет",
                   style = MaterialTheme.typography.titleMedium
               )
            }
            Button( // кнопка перехода на экран выбора иконки
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .border(
                        width = 2.dp,
                        color = MainColor,
                        shape = RoundedCornerShape(20)
                    ),
                onClick =
                {
                    showIconPicker = true // вызов окна выбора иконки
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MainColor
                )
            )
            {
                Text(
                    "Выбрать иконку",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(0.8f).padding(vertical = 20.dp),
                text = "Иконка",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.20f)) // отступ от текста "Иконка"
            CategoryIcon(
                icon = categoryIcon,
                title = categoryName,
                color = categoryColor,
                isEnabled = false,
                size = 256.dp,
            )
        }
    }
}

/**
 * Диалоговое окно выбора цвета иконки категории.
 * - onColorPicked: действие, которое должно произойти, когда пользователь выбрал цвет
 */
@Composable
fun ColorPickerDialog(onColorPicked: (ULong) -> Unit, onExited: () -> Unit)
{
    // контроллер цвета, хранящий выбранный пользователем цвет
    val con = rememberColorPickerController()

    var showPresetColorPickerDialog by remember { mutableStateOf(false) }

    if (showPresetColorPickerDialog)
    {
        PresetColorPickerDialog(
            onColorPicked =
            {
                onColorPicked(it)
                onExited()
            },
            onExited = { showPresetColorPickerDialog = false }
        )
    }

    AlertDialog( // встроенная функция диалогового окна
        // действие, которое происходит когда пользователь нажал на место, вне окна выбора цвета
        onDismissRequest = onExited,
        title = // заголовок окна
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = "Выбрать цвет",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        text = // содержание окна
        {
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                HsvColorPicker( // палитра выбора цвета
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .padding(10.dp),
                    controller = con
                )

                AlphaSlider( // ползунок прозрачности цвета
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(10.dp)
                        .height(30.dp),
                    controller = con,
                )

                BrightnessSlider( // ползунок яркости цвета
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(10.dp)
                        .height(30.dp),
                    controller = con,
                    wheelColor = Color.Black
                )

                AlphaTile( // тайл, отобраюащий выбранный цвет
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    controller = con
                )
                TextButton(
                    onClick = { showPresetColorPickerDialog = true }
                )
                {
                    Text(
                        text = "Выбрать готовый цвет",
                        color = MainColor,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        },
        confirmButton = // кнопка "Готово"
        {
            TextButton(
                onClick = // возвращаем выбранный цвет и закрываем окно
                { 
                    onColorPicked(con.selectedColor.value.value)
                    onExited()
                } 
            )
            {
                Text(
                    text = "Готово",
                    color = MainColor,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        },
        dismissButton = // кнопка "Отмена"
        {
            TextButton(onClick = onExited) // просто закрываем окно
            {
                Text(
                    text = "Отмена",
                    color = MainColor,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    )
}

/**
 * Диалоговое окно выбора иконки категории.
 * - onIconPicked: действие, которое должно произойти, когда пользователь выбрал иконку.
 * Возвращает выбранную иконку
 * - onExited: действие, которое должно произойти при закрытии окна
 */
@Composable
fun IconPickerDialog(onIconPicked: (Int) -> Unit, onExited: () -> Unit)
{
    AlertDialog( // встроенная функция диалогового окна
        // действие, которое происходит когда пользователь нажал на место, вне окна выбора иконки
        onDismissRequest = onExited,
        title = // заголовок окна
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = "Каталог иконок",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        text = // содержание окна
        {
            LazyVerticalGrid ( // таблица иконок
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                columns = GridCells.Adaptive(minSize = 100.dp),
                modifier = Modifier.fillMaxWidth()
            )
            {
                items(ContentManager.getIcons())
                { item ->
                    CategoryIcon(
                        icon = item,
                        onClick =
                        {
                            onIconPicked(item)
                            onExited()
                        }
                    )
                }
            }
        },
        confirmButton = {}, // не используется и нельзя убрать (AlertDialog не позволяет)
        dismissButton = // кнопка "Отмена"
        {
            TextButton(onClick = onExited) // просто закрываем окно
            {
                Text(
                    text = "Отмена",
                    color = MainColor,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    )
}

/**
 * Диалоговое окно выбора предустановленного цвета.
 * - onColorPicker: возвращает выбранный цвет
 * - onExited: действие при выходе
 */
@Composable
fun PresetColorPickerDialog(onColorPicked: (ULong) -> Unit, onExited: () -> Unit)
{
    AlertDialog( // встроенная функция диалогового окна
        // действие, которое происходит когда пользователь нажал на место, вне окна выбора цвета
        onDismissRequest = onExited,
        title = // заголовок окна
        {
            Row( // контейнер заголовка
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = "Каталог цветов",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
        text = // содержание окна
        {
            LazyVerticalGrid ( // таблица цветов
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                columns = GridCells.Adaptive(minSize = 86.dp),
                modifier = Modifier.fillMaxWidth()
            )
            {
                items(ContentManager.getColor()) // перебираем список цветов приложения.
                { item ->
                    AlphaTile( // тайл, отображающий выбранный цвет
                        modifier = Modifier
                            .size(86.dp)
                            .padding(8.dp)
                            .clickable { // делаем компонент "кликабельным"
                                onColorPicked(item.value) // возвращаем выбраный цвет
                                onExited() // выходим
                            },
                        selectedColor = item,
                    )
                }
            }
        },
        confirmButton = {}, // не используется и нельзя убрать (AlertDialog не позволяет)
        dismissButton = // кнопка "Отмена"
        {
            TextButton(onClick = onExited) // просто закрываем окно
            {
                Text(
                    text = "Отмена",
                    color = MainColor,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    )
}

/**
 * Функция отображения категории и иконок категорий.
 * - icon: иконка категории
 * - title: название иконки, может быть пустым
 * при отображении встроенных иконок в каталоге иконок
 * - color: цвет заднего фона
 * - onClick: действие, которое должно произойти по клику на иконку
 * (по умолчанию - нет действия)
 * - isEnabled: включена ли возможность клика по иконки
 * (используется, когда иконку нужно просто отобразить и ничего не делать при клике по ней)
 * - size: размер иконки
 */
@Composable
fun CategoryIcon(
    icon: Int,
    title: String? = null,
    color: ULong = MainColor.value,
    onClick: () -> Unit = {},
    isEnabled: Boolean = true,
    size: Dp = 86.dp
)
{
    Button( // кнопка иконки (чтобы была возможность клика по ней)
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        shape = CircleShape,
        onClick = onClick,
        enabled = isEnabled,
        modifier = Modifier.size(size)
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Icon( // сама иконка
                modifier = Modifier
                    .background(color = Color(color), shape = CircleShape)
                    .padding(12.dp),
                tint = Color.White,
                painter = painterResource(icon),
                contentDescription = icon.toString()
            )

            if (title != null) // если название было, то отобразить его
            {
                Text( // текст названия категории
                    modifier = Modifier.padding(24.dp),
                    text = title,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}