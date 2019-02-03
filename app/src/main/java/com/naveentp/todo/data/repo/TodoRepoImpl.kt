package com.naveentp.todo.data.repo

import androidx.lifecycle.LiveData
import com.naveentp.todo.data.db.TodoDao
import com.naveentp.todo.data.db.TodoRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author Naveen T P
 * @since 08/11/18
 */
class TodoRepoImpl(private val todoDao: TodoDao) : TodoRepo {

    override fun saveTodo(todo: TodoRecord) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                todoDao.saveTodo(todo)
            }
        }
    }

    override fun updateTodo(todo: TodoRecord) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                todoDao.updateTodo(todo)
            }
        }
    }


    override fun deleteTodo(todo: TodoRecord) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                todoDao.deleteTodo(todo)
            }
        }
    }

    override fun getAllTodoList(): LiveData<List<TodoRecord>> {
        return todoDao.getAllTodoList()
    }
}