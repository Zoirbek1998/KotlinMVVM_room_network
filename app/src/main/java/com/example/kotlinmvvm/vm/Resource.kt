package com.example.kotlinmvvm.vm

sealed class Resource<T> {

    class Loading<T> : Resource<T>()
    class Error<T : Any>(val e: Throwable) : Resource<T>()
    class Success<T : Any>(val data: T) : Resource<T>()

}
