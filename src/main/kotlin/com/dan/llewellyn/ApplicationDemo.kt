package com.dan.llewellyn

import com.dan.llewellyn.interfaces.Application

class ApplicationDemo : Application {

    override fun listOfActions(): Map<String, (List<Pair<String, String>>?) -> Response> =
            mapOf("demointent" to {
                    _ -> Response("Demo intent", ResponseType.TELL_RESPONSE, true)
                }
            )




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
