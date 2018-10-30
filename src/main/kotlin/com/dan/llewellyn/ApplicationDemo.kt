package com.dan.llewellyn

import com.dan.llewellyn.interfaces.Application
import com.dan.llewellyn.interfaces.IntentHandler

class ApplicationDemo : Application {

    override fun listOfActions(): Map<String, IntentHandler> =
            mapOf("demointent" to { (slots) ->
                    Response("Demo intent", ResponseType.TELL_RESPONSE, true)
                })




    override fun respondWithLinkCard(): AppLinkCard? = null

    override fun respondWithWelcomeMessage() : AppWelcomeCard = AppWelcomeCard(
            "Welcome to the demo application",
            "Welcome to the demo application",
            "Welcome to the demo application")

    override fun getProperties(): AppProperties {

        return AppProperties(
                isAccountLinkingRequired = false
        )
    }

}
