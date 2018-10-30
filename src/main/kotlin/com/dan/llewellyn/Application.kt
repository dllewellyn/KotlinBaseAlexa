package com.dan.llewellyn


enum class ResponseType {
    ASK_RESPONSE,
    TELL_RESPONSE
}

/**
 * Properties to implement for the application
 */
data class AppProperties(val isAccountLinkingRequired : Boolean)

/**
 * App link card
 */
data class AppLinkCard(val title : String, val linkCardText : String)

/**
 * App welcome card
 */
data class AppWelcomeCard(val title : String, val speech : String, val cardText : String)

data class Response(val outputSpeech: String,
                    val responseType : ResponseType,
                    val withCard : Boolean = true,
                    val reprompt : String? = null)


/**
 * Functions required to implement for the app
 */
abstract class Application {

    abstract fun getProperties() : AppProperties
    abstract fun respondWithLinkCard(): AppLinkCard?
    abstract fun respondWithWelcomeMessage(): AppWelcomeCard
    abstract fun listOfActions() : Map<String, ((List<Pair<String, String>>) -> Response)>
}



