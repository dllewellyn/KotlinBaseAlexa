package com.dan.llewellyn.interfaces

import com.dan.llewellyn.AppLinkCard
import com.dan.llewellyn.AppProperties
import com.dan.llewellyn.AppWelcomeCard
import com.dan.llewellyn.Response


/**
 * Functions required to implement for the app
 */
interface Application {

    fun getProperties() : AppProperties
    fun respondWithLinkCard(): AppLinkCard?
    fun respondWithWelcomeMessage(): AppWelcomeCard
    fun listOfActions() : Map<String, (List<Pair<String, String>>) -> Response>
}
