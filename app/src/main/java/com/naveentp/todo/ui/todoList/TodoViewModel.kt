package com.naveentp.todo.ui.todoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.naveentp.todo.data.db.TodoRecord
import com.naveentp.todo.data.repo.TodoRepo

/**
 * @author Naveen T P
 * @since 08/11/18
 */
class TodoViewModel(private val todoRepo: TodoRepo) : ViewModel() {

    fun saveTodo(todo: TodoRecord) {
        todoRepo.saveTodo(todo)
    }

    fun updateTodo(todo: TodoRecord){
        todoRepo.updateTodo(todo)
    }

    fun deleteTodo(todo: TodoRecord) {
        todoRepo.deleteTodo(todo)
    }

    fun getAllTodoList(): LiveData<List<TodoRecord>> {
        return todoRepo.getAllTodoList()
    }

}
