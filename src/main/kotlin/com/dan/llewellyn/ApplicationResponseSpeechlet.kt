package com.dan.llewellyn

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.*
import com.amazon.speech.speechlet.interfaces.system.SystemInterface
import com.amazon.speech.speechlet.interfaces.system.SystemState
import com.amazon.speech.speechlet.services.DirectiveServiceClient

class ApplicationResponseSpeechlet(val directiveServiceClient: DirectiveServiceClient, val app: MyApplication) : SpeechletV2 {

    override fun onSessionEnded(requestEnvelope: SpeechletRequestEnvelope<SessionEndedRequest>?) {
    }

    /**
     * Called when a user interacts with the application
     */
    override fun onIntent(requestEnvelope: SpeechletRequestEnvelope<IntentRequest>?): SpeechletResponse {

        val returnValue : SpeechletResponse

        if (this.app.getProperties().isAccountLinkingRequired) {

            val accessToken : String? = getSystemState(requestEnvelope!!.context).user.accessToken

            if (accessToken == null) {
                returnValue = this.app.respondWithLinkCard()
            }

        }
    }

    override fun onLaunch(requestEnvelope: SpeechletRequestEnvelope<LaunchRequest>?): SpeechletResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSessionStarted(requestEnvelope: SpeechletRequestEnvelope<SessionStartedRequest>?) {

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
