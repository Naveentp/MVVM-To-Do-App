# MVVM-To-Do-App
> A To-Do application written in kotlin using Android Architectural components

## What's new?  
**Room + Coroutines** - Upgraded Room to v2.1. Room's DAO methods can now be suspend functions.  
**Koin Integration** - A new branch [Koin in Action](https://github.com/Naveentp/MVVM-To-Do-App/tree/koin_in_action) created to demonstrate [Koin](https://insert-koin.io/) dependency injection.

 - **Kotlin** - as a programming language.
 - **ViewModel** - Used to manage UI related data, Survives config changes.
 - **LiveData** - Lifecycle aware Observable data holder class.
 - **Room** - To persist user todo records
 - **Coroutines** - To query db in worker thread.
 - **AndroidX** - Project uses androidx packages.
 - **Parcelize** - App uses this koltin's experimental feature to convert Object into streams of bytes while passing from one activity to other.

#### Functionalities included:
- Create Todo
- Delete existing Todo
- View/Update Todo
- Search Todo list
