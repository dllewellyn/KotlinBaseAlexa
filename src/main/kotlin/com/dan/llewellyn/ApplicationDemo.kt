package com.dan.llewellyn

import com.dan.llewellyn.base.*
import com.dan.llewellyn.interfaces.AbstractApplication
import com.dan.llewellyn.interfaces.Application

class ApplicationDemo : AbstractApplication() {

    override fun respondToEnd(): Resp {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun respondToHelp(): Resp {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listOfActions(): Map<String, (Map<String, String>) -> Resp> =
            mapOf("demointent" to {
                    _ -> response {
                        speech = "Demo intent"
                        endSession = true

                        card {
                            text = "Demo intent"
                            title = "Deko"
                        }
                    }
                }
            )

    override fun respondWithLinkCard(): AppLinkCard? = null

    override fun respondWithWelcomeMessage() : Resp = response {
        speech = "Welcome to the demo application"
        endSession = true

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
