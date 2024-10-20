package com.example.myfinance.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myfinance.CategoryListViewModel
import com.example.myfinance.FinanceDao
import com.example.myfinance.OperationListViewModel
import com.example.myfinance.OperationModel
import com.example.myfinance.OperationType
import com.example.myfinance.StateManager
import kotlinx.coroutines.launch
import java.util.Calendar

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
    operationVM: OperationListViewModel = viewModel(),
    onOperationSaved: () -> Unit
)
{
    val context = LocalContext.current // текущий контекст
    val scope = rememberCoroutineScope()

    // значение суммы - загружаем сумму из StateManager,
    // если вернется пустая строка, то sum, будет равно "" (пустая строка),
    // что подходит как значение по умолчанию
    var sum by remember { mutableStateOf(StateManager.loadStr(context, "sum")) }

    // значение даты
    var date by remember { mutableLongStateOf(Calendar.getInstance().timeInMillis) }

    if (StateManager.loadLong(context, "date") != 0L)
        date = StateManager.loadLong(context, "date")

    // флаг: были ли ошибки ввода
    var hasTypeErrors by remember { mutableStateOf(false) }

    // флаг: были ли ошибки при выборе категории
    var hasCategoryError by remember { mutableStateOf(false) }

    // флаг для переключателей типа операции (доходы или расходы)
    var operationTypeChecked by remember { mutableStateOf(StateManager.loadBool(context, "type")) }

    // флаг для показа окна выбора даты
    var showDatePicker by remember { mutableStateOf(false) }

    var operationType by remember { mutableStateOf(OperationType.Incomes) }

    if (showDatePicker) // если нажата кнопка "Выбрать дату"
    {
        DatePickerDialog( // показываем окно выбора даты
            onDateSelected =
            {
                date = it
            },
            onExit = { showDatePicker = false }
        )
    }

    Scaffold( // разметка данного экрана
        topBar = // верхняя панель экрана
        {
            TopBar(
                screenTitle = "Добавление операции",
                onNavIconClicked = // возврат к пред. экрану
                {
                    nav.navigate("main_screen") {
                        popUpTo("main_screen") {
                            inclusive = true
                        }
                    }
                }
            )
        },
        bottomBar = // нижняя панель экрана
        {
            BottomBar(
                onActionButtonClicked = // кнопка "Добавить операцию"
                {
                    // ошибки были, если:
                    // не введена сумма
                    if(sum.isBlank())
                        hasTypeErrors = true
                    // не выбрана категория
                    if (CategoryListViewModel.SelectedCategory == null)
                        hasCategoryError = true

                    if (!hasTypeErrors && !hasCategoryError) // если не было ошибок
                    {
                        scope.launch()
                        {
                            if (!operationTypeChecked)
                                operationType = OperationType.Expenses

                            val newOperation = OperationModel( // то, создаем операцию как програмнный объект
                                categoryId = CategoryListViewModel.SelectedCategory!!.id,
                                sum = sum.toInt(),
                                date = date,
                                type = operationType.name
                            )
                            dao.insertOperation(newOperation) // делаем вставку операции в БД
                            operationVM.add(newOperation) // и добавляем ее в список операций
                            CategoryListViewModel.SelectedCategory = null // обнуляем выбранную категорию
                            onOperationSaved()
                        }
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
            Row( // контейнер суммы
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                val sumLimit = 12 // ограничение максимальной суммы
                TextField( // поле ввода суммы
                    modifier = Modifier.fillMaxWidth(0.35f),
                    singleLine = true,
                    value = sum,
                    onValueChange =
                    {
                        sum = it.take(sumLimit)

                        // если ранее были ошибки, но теперь поле ввода не пустое, то ошибок теперь - нет
                        if (hasTypeErrors && sum.isNotBlank())
                            hasTypeErrors = false

                        // если пользователь ввел 0 в начале и длина суммы больше двух
                        if (sum.startsWith('0') && sum.length >= 2)
                            sum = "${sum[1]}${sum[0]}" // то поменять второй и первый символ местами
                                                       // например, было 02, а станет 20
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    colors = getDefaultTextFieldColors(),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    isError = hasTypeErrors,
                )
                Text(
                    text = "РУБ",
                    color = MainColor,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            if (hasTypeErrors) // отображаем ошибку, если она была
                ErrorText(
                    text = "Некорректное значение суммы",
                    align = TextAlign.Center
                )
            Row( // контейнер переключателей типа операции
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            )
            {
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MainColor
                    ),
                    selected = operationTypeChecked,
                    onClick =
                    {
                        operationTypeChecked = true
                    }
                )
                Text("Расходы")
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MainColor
                    ),
                    selected = !operationTypeChecked,
                    onClick =
                    {
                        operationTypeChecked = false
                    }
                )
                Text("Доходы")
            }
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Button( // кнопка перехода на экран выбора категории
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .border(
                        width = 2.dp,
                        color = MainColor,
                        shape = RoundedCornerShape(20)
                    ),
                onClick = // переход на экрана выбора категории
                {
                    StateManager.saveStr(context, "sum", sum) // сохраняем сумму
                    StateManager.saveLong(context, "date", date) // сохраняем дату
                    StateManager.saveBool(context, "type", operationTypeChecked)
                    nav.navigate("category_choosing") { // переходим на экран
                        popUpTo("category_choosing") {
                            inclusive = true
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MainColor
                )
            )
            {
                Text(
                    "Выбрать категорию",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Button( // кнопка перехода на экран выбора категории
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .border(
                        width = 2.dp,
                        color = MainColor,
                        shape = RoundedCornerShape(20)
                    ),
                onClick = { showDatePicker = true }, // показать окно выбора даты
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MainColor
                )
            )
            {
                Text(
                    "Выбрать дату",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = "Дата: ${dateToStr(date)}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MainColor
            )
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = "Категория",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MainColor
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.25f))

            if (hasCategoryError) // отображаем ошибку, если она была
                ErrorText("Выберите категорию")
            else if (CategoryListViewModel.SelectedCategory != null)
            {
                hasCategoryError = false // если категория выбрана, то ошибок больше нет
                CategoryIcon(
                    icon = CategoryListViewModel.SelectedCategory!!.icon,
                    title = CategoryListViewModel.SelectedCategory!!.title,
                    color = CategoryListViewModel.SelectedCategory!!.color.toULong(),
                    isEnabled = false
                )
            }
        }
    }
}

/**
 * Функция выбора даты
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(onDateSelected: (Long) -> Unit, onExit: () -> Unit)
{
    // значение пикера даты равно текущей дате календаря
    val dps = rememberDatePickerState(initialSelectedDateMillis = Calendar.getInstance().timeInMillis)

    DatePickerDialog( // функция показа даты как диалогового окна
        onDismissRequest = onExit,
        confirmButton =
        {
            TextButton( // возвращяем выбранную дату и выходим
                onClick =
                {
                    // возвращаем выбранную дату
                    onDateSelected(dps.selectedDateMillis!!)
                    onExit()
                }
            )
            {
                Text("OK", color = MainColor)
            }
        },
        dismissButton =
        {
            TextButton(onClick = onExit) // просто выходим
            {
                Text("Отмена", color = MainColor)
            }
        }
    )
    {
        DatePicker( // сам DatePicker
            state = dps,
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = MainColor,
                todayDateBorderColor = MainColor,
                todayContentColor = Color.Black,
                dateTextFieldColors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MainColor,
                    focusedLabelColor = MainColor,
                    cursorColor = MainColor
                )
            )
        )
    }
}

/**
 * Преобразовывает значение даты в строку
 * (дата возвращается в следующем формате: ДД.ММ.ГГГГ).
 * - date: значение даты
 * - sep: разделитель для значений даты, по умолчанию установлен в '.'
 */
private fun dateToStr(date: Long, sep: Char = '.'): String
{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    return "${calendar.get(Calendar.DAY_OF_MONTH)}${sep}" +
            "${calendar.get(Calendar.MONTH) + 1}${sep}" +
            "${calendar.get(Calendar.YEAR)}"
}