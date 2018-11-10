package com.dan.llewellyn.base

/**
 * ASK Response - This response is used when you want more user information
 * TELL Response - This response is when you do not want any more user information
 */
enum class ResponseType {
    ASK_RESPONSE,
    TELL_RESPONSE
}
data class Response(var speech : String? = null,
                    var card : Card? = null,
                    var reprompt: String? = null,
                    var endSession : Boolean = true)

fun response(block : Response.() -> Unit) : Response {
    val response = Response()
    response.block()
    return response
}