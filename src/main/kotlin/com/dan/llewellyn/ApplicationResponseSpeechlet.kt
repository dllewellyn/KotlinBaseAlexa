package com.dan.llewellyn

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.*
import com.amazon.speech.speechlet.interfaces.system.SystemInterface
import com.amazon.speech.speechlet.interfaces.system.SystemState
import com.amazon.speech.speechlet.services.DirectiveServiceClient

class ApplicationResponseSpeechlet(val directiveServiceClient: DirectiveServiceClient, val app: Application) : SpeechletV2 {

    override fun onSessionEnded(requestEnvelope: SpeechletRequestEnvelope<SessionEndedRequest>?) {
    }

    /**
     * Called when a user interacts with the application
     */
    override fun onIntent(requestEnvelope: SpeechletRequestEnvelope<IntentRequest>?): SpeechletResponse {

        val intent = requestEnvelope?.request?.intent ?: throw SpeechletException("Invalid intent")
        val intentName = intent.name ?: throw SpeechletException("Invalid intent")

        val slotValues = intent.slots
                .map { Pair(it.key, it.value.value)}

        return this.app.listOfActions()[intentName]?.invoke(slotValues)?.toSpeechlet()
                ?: throw SpeechletException("Invalid intent")
    }


    override fun onLaunch(requestEnvelope: SpeechletRequestEnvelope<LaunchRequest>?): SpeechletResponse {

        if (this.app.getProperties().isAccountLinkingRequired) {

            val accessToken : String? = getAccessToken(requestEnvelope)

            if (accessToken == null) {
                this.app.respondWithLinkCard()?.let {
                    return it.toSpeechlet()
                }
            }
        }

        return this.app.respondWithWelcomeMessage().toAskSpeechlet()
    }

    override fun onSessionStarted(requestEnvelope: SpeechletRequestEnvelope<SessionStartedRequest>?) {

    }


    /**
     * Get the access token. May be null.
     */
    private fun getAccessToken(requestEnvelope: SpeechletRequestEnvelope<LaunchRequest>?) : String? {
        return getSystemState(requestEnvelope!!.context).user.accessToken
    }

    /**
     * Helper method that retrieves the system state from the request context.
     * @param context request context.
     * @return SystemState the systemState
     */
    private fun getSystemState(context: Context): SystemState {
        return context.getState(SystemInterface::class.java, SystemState::class.java)
    }

}
