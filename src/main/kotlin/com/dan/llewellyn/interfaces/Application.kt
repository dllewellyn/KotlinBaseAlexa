package com.dan.llewellyn.interfaces

import com.dan.llewellyn.base.AppLinkCard
import com.dan.llewellyn.base.AppProperties
import com.dan.llewellyn.base.Resp

/**
 * Functions required to implement for the app
 */
interface Application {

    fun getProperties() : AppProperties
    fun respondWithLinkCard(): AppLinkCard?

    fun respondWithWelcomeMessage(): Resp
    fun listOfActions() : Map<String, (Map<String, String>) -> Resp>
    fun respondToEnd() : Resp
    fun respondToHelp(): Resp

    fun setSessionId(sessionId : String)
    fun getSessionId() : String

    fun userId() : String
    fun setUserId(userId : String)

}
