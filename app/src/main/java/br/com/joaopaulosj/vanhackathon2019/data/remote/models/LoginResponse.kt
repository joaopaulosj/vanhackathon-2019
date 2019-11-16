package br.com.joaopaulosj.vanhackathon2019.data.remote.models

data class LoginResponse(
        val id: String?,
        val name: String?,
        val last_name: String?,
        val isPremium: Boolean?,
        val email: String?
)