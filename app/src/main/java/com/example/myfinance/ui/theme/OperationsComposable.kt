package com.example.myfinance.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myfinance.FinanceDao

// Данный файл содержит все функции пользовательского интерфейса,
// связанные с главным экраном и операциями

@Composable
fun MainScreen(
   nav: NavController,
)
{
    Scaffold( // разметка данного экрана
        topBar = // верхняя панель экрана
        {
            TopBar(
                screenTitle = "Мои финансы",
                isMainScreen = true
            )
        },
        bottomBar = // нижняя панель экрана
        {
            BottomBar(
                onActionButtonClicked = // кнопка добавления операции
                {
                    nav.navigate("operation_adding") {
                        popUpTo("operation_adding") {
                            inclusive = true
                        }
                    }
                },
                actionButtonIcon = Icons.Default.Add
            )
        },
    )
    { padding ->
        Column(modifier = Modifier.padding(padding)) {  }
    }
}

/**
 * Экран создания категории.
 * - nav: контроллер для перехода к другим экранам, или возврата на главный
 * - onBuilt: возвращает созданную категорию
 */
@Composable
fun OperationAddingScreen(
    nav: NavController,
    dao: FinanceDao,
    onExited: () -> Unit,
    )
{
    var sum by remember { mutableStateOf("") }
    var hasErrors by remember { mutableStateOf(false) }

    Scaffold( // разметка данного экрана
        topBar = // верхняя панель экрана
        {
            TopBar(
                screenTitle = "Добавление операции",
                onNavIconClicked = onExited // возврат к пред. экрану
            )
        },
        bottomBar = // нижняя панель экрана
        {
            BottomBar(
                onActionButtonClicked = // кнопка "Галочка"
                {
                    if(sum.isBlank())
                        hasErrors = true
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                TextField( // поле ввода суммы
                    modifier = Modifier.fillMaxWidth(0.35f),
                    singleLine = true,
                    value = sum,
                    onValueChange =
                    {
                        sum = it

                        // если ранее были ошибки, но теперь поле ввода не пустое,
                        // то ошибок теперь - нет
                        if (hasErrors && sum.isNotBlank())
                            hasErrors = false
                    },
                    placeholder =
                    {
                        Text(
                            "0",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black.copy(0.5f),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    colors = getDefaultTextFieldColors(),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    isError = hasErrors,
                    supportingText =
                    {
                        if (hasErrors) // отображаем ошибку, если она была
                        {
                            Text(
                                "Введите сумму",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                )
                Text(
                    text = "РУБ",
                    color = MainColor,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
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
                onClick =
                {
                    nav.navigate("category_choosing") {
                        popUpTo("category_choosing") {
                            inclusive = true
                        }
                    }
                }, // вызов окна выбора цвета
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
        }
    }
}