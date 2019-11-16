package br.com.mobile2you.m2ybase.data.remote.models

data class LoginRequest(
        val email: String?,
        val password: String? = null
)