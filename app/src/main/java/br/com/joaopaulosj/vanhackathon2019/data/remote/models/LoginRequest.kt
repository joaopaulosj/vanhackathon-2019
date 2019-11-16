package br.com.joaopaulosj.vanhackathon2019.data.remote.models

data class LoginRequest(
        val email: String?,
        val password: String? = null
)