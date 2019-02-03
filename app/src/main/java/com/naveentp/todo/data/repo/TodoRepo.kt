package com.naveentp.todo.data.repo

import androidx.lifecycle.LiveData
import com.naveentp.todo.data.db.TodoRecord

/**
 * @author Naveen T P
 * @since 03/02/19
 */
interface TodoRepo {
    fun saveTodo(todo: TodoRecord)
    fun updateTodo(todo: TodoRecord)
    fun deleteTodo(todo: TodoRecord)
    fun getAllTodoList(): LiveData<List<TodoRecord>>
}