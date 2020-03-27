package tat.mukhutdinov.challenger.infrastructure.structure

import kotlinx.serialization.Serializable

@Serializable
class Response(val result: ResultResponse? = null, val error: ErrorResponse? = null) {

    val isSuccessful = error == null
}

@Serializable
class ErrorResponse(val code: Int, val message: String)

@Serializable
open class ResultResponse