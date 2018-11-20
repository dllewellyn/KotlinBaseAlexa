package com.dan.llewellyn.base

import com.dan.llewellyn.display.BuilderTemplate2.BuilderTemplate

/**
 * ASK Response - This response is used when you want more user information
 * TELL Response - This response is when you do not want any more user information
 */
enum class ResponseType {
    ASK_RESPONSE,
    TELL_RESPONSE
}

data class Response(var speech : String,
                    var card : Card? = null,
                    var reprompt: String? = null,
                    var endSession : Boolean = true,
                    var directives : List<Directive>? = null,
                    var template : BuilderTemplate?= null)

fun response(speech : String, block : Response.() -> Unit) : Response {
    val response = Response(speech)
    response.block()
    return response
}