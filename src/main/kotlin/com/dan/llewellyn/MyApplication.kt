package com.dan.llewellyn


/**
 * Properties to implement for the application
 */
data class AppProperties(val isAccountLinkingRequired : Boolean)

/**
 * App link card
 */
data class AppLinkCard(val linkCardText : String)

/**
 * Functions required to implement for the app
 */
abstract class MyApplication {

    abstract fun getProperties() : AppProperties
    abstract fun respondWithLinkCard(): AppLinkCard
    
}

