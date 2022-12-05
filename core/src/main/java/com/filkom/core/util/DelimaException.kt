package com.filkom.core.util

class DelimaException(private val customerMessage:String?){
    fun getMessage() = customerMessage ?: "Something went wrong. Try again later"
}