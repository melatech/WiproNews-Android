package com.melatech.wipronews.data.util

/**
 *created by Jason Junior Calvert on 26/01/2022.
 */
//This sealed class is used to track the network state
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T): Resource<T>(data)
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}