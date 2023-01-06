package com.filkom.core.model.domain

data class UserDomain(
    val user_id:String,
    val nama:String,
    val nomor_handphone:String,
    val alamat:String,
    val saldo:Int,
    val avatar:String
)
