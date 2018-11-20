package com.dan.llewellyn.interfaces

import com.dan.llewellyn.base.AppLinkCard
import com.dan.llewellyn.base.AppProperties
import com.dan.llewellyn.base.Response

/**
 * Functions required to implement for the app
 */
interface Application {

    fun getProperties() : AppProperties
    fun respondWithLinkCard(): AppLinkCard?

    fun respondToStart(): Response
    fun listOfActions() : Map<String, (Map<String, String>) -> Response>
    fun respondToEnd() : Response
    fun respondToHelp(): Response

    fun setSessionId(sessionId : String)
    fun getSessionId() : String

    fun userId() : String
    fun setUserId(userId : String)

    fun getAttributes() : Map<String, Any>
    fun updateAttributes(attributes : Map<String, Any>)

}
