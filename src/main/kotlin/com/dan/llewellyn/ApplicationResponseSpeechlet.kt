//package com.dan.llewellyn
//
//import com.amazon.ask.model.IntentRequest
//import com.amazon.speech.json.SpeechletRequestEnvelope
//import com.amazon.speech.speechlet.*
//import com.amazon.speech.speechlet.interfaces.system.SystemInterface
//import com.amazon.speech.speechlet.interfaces.system.SystemState
//import com.amazon.speech.speechlet.services.DirectiveServiceClient
//import com.dan.llewellyn.interfaces.Application
//
//class ApplicationResponseSpeechlet(val directiveServiceClient: DirectiveServiceClient, val app: Application) : SpeechletV2 {
//
//    override fun onSessionEnded(requestEnvelope: SpeechletRequestEnvelope<SessionEndedRequest>?) {
//    }
//
//    /**
//     * Called when a user interacts with the application
//     */
//    override fun onIntent(requestEnvelope: SpeechletRequestEnvelope<IntentRequest>?): SpeechletResponse {
//
//        val intent = requestEnvelope?.request?.intent ?: throw SpeechletException("Invalid intent")
//        val intentName = intent.name?.toLowerCase() ?: throw SpeechletException("Invalid intent")
//
//        val slotValues = intent.slots
//                .map { Pair(it.key, it.value.value)}
//
//        println("Intent name $intentName")
//        val intentAction = this.app.listOfActions()[intentName]
//                ?: throw SpeechletException("Invalid intent ($intentName) expected ${this.app.listOfActions().keys}")
//
//        print("Executing $intentAction")
//        return intentAction(slotValues).toSpeechlet()
//    }
//
//
//    override fun onLaunch(requestEnvelope: SpeechletRequestEnvelope<LaunchRequest>?): SpeechletResponse {
//
//        if (this.app.getProperties().isAccountLinkingRequired) {
//
//            val accessToken : String? = getAccessToken(requestEnvelope)
//
//            if (accessToken == null) {
//                this.app.respondWithLinkCard()?.let {
//                    return it.toSpeechlet()
//                }
//            }
//        }
//
//        return this.app.respondToStart().toSpeechlet()
//    }
//
//    override fun onSessionStarted(requestEnvelope: SpeechletRequestEnvelope<SessionStartedRequest>?) {
//
//    }
//
//
//    /**
//     * Get the access token. May be null.
//     */
//    private fun getAccessToken(requestEnvelope: SpeechletRequestEnvelope<LaunchRequest>?) : String? {
//        return getSystemState(requestEnvelope!!.context).user.accessToken
//    }
//
//    /**
//     * Helper method that retrieves the system state from the request context.
//     * @param context request context.
//     * @return SystemState the systemState
//     */
//    private fun getSystemState(context: Context): SystemState {
//        return context.getState(SystemInterface::class.java, SystemState::class.java)
//    }
//
//}
