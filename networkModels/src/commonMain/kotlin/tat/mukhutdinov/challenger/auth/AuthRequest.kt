package tat.mukhutdinov.challenger.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(val idToken: String)
