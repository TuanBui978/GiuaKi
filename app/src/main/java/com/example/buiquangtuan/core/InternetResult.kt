package com.example.buiquangtuan.core

import java.lang.Exception

sealed class InternetResult<out T> {
    data class Failed(val exception: Exception): InternetResult<Nothing>()
    data class Success<T>(val data: T): InternetResult<T>()
}