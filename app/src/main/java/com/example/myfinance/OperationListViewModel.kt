package com.example.myfinance

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * Представляет автоматически обновляющийся список операций.
 */
class OperationListViewModel : ViewModel() {

    /**
     * Список операций.
     */
    private val _operations = mutableStateOf(listOf<OperationModel>())

    /**
     * Состояние списка операций.
     */
    val operationList: State<List<OperationModel>> = _operations

    /**
     * Добавить операцию в список.
     */
    fun add(operationModel: OperationModel) {
        _operations.value += operationModel
    }

    /**
     * Удалить операцию из списка.
     */
    fun remove(operationModel: OperationModel) {
        _operations.value -= operationModel
    }

    /**
     * Выполняет замену старой операции на новую по id.
     */
    fun replace(id: Int, operationModel: OperationModel) {
        remove(_operations.value.find { it.id == id }!!) // удаляем старую категорию из списка по id
        operationModel.id = id // присваиваем id старой категории новой
        add(operationModel) // добавляем новую категорию в список
    }

    /**
     * Скопировать данные из списка.
     */
    fun copyFrom(list: List<OperationModel>) {
        list.forEach { _operations.value += it }
    }
}