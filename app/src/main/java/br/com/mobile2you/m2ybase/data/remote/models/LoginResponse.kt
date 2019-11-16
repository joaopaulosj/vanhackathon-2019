package br.com.mobile2you.m2ybase.data.remote.models

data class LoginResponse(
        val id: String?,
        val name: String?,
        val last_name: String?,
        val isPremium: Boolean?,
        val email: String?
)