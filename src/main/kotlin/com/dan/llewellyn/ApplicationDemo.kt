package com.dan.llewellyn

import com.dan.llewellyn.base.*
import com.dan.llewellyn.interfaces.AbstractApplication

class ApplicationDemo : AbstractApplication() {

    override fun respondToEnd(): Response {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun respondToHelp(): Response {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listOfActions(): Map<String, (Map<String, String>) -> Response> =
            mapOf("demointent" to {
                    _ -> response {
                        speech = "Demo intent"
                        endSession = true

                        card {
                            text = "Demo intent"
                            title = "Demo"
                        }
                    }
                }
            )

    override fun respondWithLinkCard(): AppLinkCard? = null

    override fun respondToStart() : Response = response {
        speech = "Welcome to the demo application"
        endSession = false

        card {
            text = "Welcome to the demo application"
            title = "Deko"
        }
    }

    override fun getProperties(): AppProperties {

        return AppProperties(
                isAccountLinkingRequired = false
        )
    }
}
