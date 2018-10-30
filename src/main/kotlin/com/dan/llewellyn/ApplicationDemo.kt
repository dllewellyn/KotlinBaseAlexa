package com.dan.llewellyn

class ApplicationDemo : Application() {

    override fun listOfActions(): Map<String, (List<Pair<String, String>>) -> Response> =
        mapOf(Pair("DemoIntent", { (slots) ->  Response(
                "Demo intent", ResponseType.TELL_RESPONSE, true)}))


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
