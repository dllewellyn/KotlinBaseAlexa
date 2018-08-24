package com.dan.llewellyn

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler
import com.amazon.speech.speechlet.services.DirectiveServiceClient
import java.util.HashSet


class Factory {

    companion object {
        fun getApp() : MyApplication {
            return MyApplicationDemo()
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
            supportedApplicationIds.add("<alexa skill id goes here>");
        }
    }
}