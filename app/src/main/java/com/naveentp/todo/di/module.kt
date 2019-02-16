package com.naveentp.todo.di

import androidx.room.Room
import com.naveentp.todo.data.db.TodoDatabase
import com.naveentp.todo.data.repo.TodoRepo
import com.naveentp.todo.data.repo.TodoRepoImpl
import com.naveentp.todo.ui.todoList.TodoViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Naveen T P
 * @since 03/02/19
 */

val applicationModule = module {

    // Room database
    single {
        Room.databaseBuilder(androidApplication(),
            TodoDatabase::class.java,
            getProperty("DB_NAME"))
            .build()
    }

    // TodoDao
    single { get<TodoDatabase>().todoDao() }

    // TodoRepository
    factory<TodoRepo> { TodoRepoImpl(get()) }

    // TodoViewModel
    viewModel { TodoViewModel(get()) }
}