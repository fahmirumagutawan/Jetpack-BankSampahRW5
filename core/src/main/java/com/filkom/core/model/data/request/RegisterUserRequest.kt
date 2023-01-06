package com.filkom.core.model.data.request

@kotlinx.serialization.Serializable
data class RegisterUserRequest(
    val nama:String,
    val nomor_handphone:String,
    val alamat:String,
    val saldo:Int,
    val avatar:String
)
