package com.dan.llewellyn

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler
import com.amazon.speech.speechlet.services.DirectiveServiceClient
import java.util.HashSet


class Factory {

    companion object {
        fun getApp() : Application {
            return ApplicationDemo()
        }
    }


}

/**
 * In point for the alexa skill
 */
class EntryPoint : SpeechletRequestStreamHandler(ApplicationResponseSpeechlet(DirectiveServiceClient(), Factory.getApp()), EntryPoint.supportedApplicationIds) {

    companion object {
        private val supportedApplicationIds = HashSet<String>()

        init {
            supportedApplicationIds.add(System.getenv("ALEXA_ID") ?: throw IllegalStateException("ALEXA_ID is required"));
        }
    }
}