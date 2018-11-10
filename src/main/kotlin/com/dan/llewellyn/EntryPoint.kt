package com.dan.llewellyn

import com.amazon.ask.SkillStreamHandler
import com.amazon.ask.Skills
import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.*

import com.dan.llewellyn.interfaces.Application
import java.util.*
import com.amazon.ask.model.dialog.DelegateDirective
import java.lang.Exception


class ApplicationAdapter(val application : Application) : RequestHandler {

    val HELP_INTENT = "amazon.helpintent"
    val LAUNCH_INTENT = "amazon.launchintent"
    val CANCEL_INTENT = "amazon.cancelintent"
    val STOP_INTENT = "amazon.stopintent"

    val buildInIntents = listOf(HELP_INTENT, LAUNCH_INTENT, CANCEL_INTENT, STOP_INTENT)

    fun getIntent(input : HandlerInput?) : String {
        val request = input?.requestEnvelope?.request

        return if (request is IntentRequest) {
            request.intent.name
        } else if (request is LaunchRequest) {
            "amazon.launchintent"
        } else {
            ""
        }
    }

    override fun canHandle(input: HandlerInput?): Boolean {
        val retVal = this.application.listOfActions().containsKey(getIntent(input)) ||
                this.buildInIntents.contains(getIntent(input))
        println("${this.application.listOfActions().keys} (can handle - ${getIntent(input)}) $retVal")
        return retVal
    }


    override fun handle(input: HandlerInput?): Optional<Response> {

        val slotValues =
            if (input?.request is IntentRequest &&
                    (input.request as IntentRequest).intent.slots != null ) {
                (input.request as IntentRequest).intent.slots
                        .map { it.key to it.value.value }
                        .associateBy({ it.first }, { it.second })
                }
            else
                mapOf()


        input?.requestEnvelope?.let { requestEnvelope ->

            if (requestEnvelope.request is IntentRequest) {

                val request = requestEnvelope.request as IntentRequest
                if (request.dialogState == DialogState.STARTED
                        || request.dialogState == DialogState.IN_PROGRESS) {

                    val directive = DelegateDirective.builder()
                            .withUpdatedIntent(request.intent)
                            .build()

                    Response.builder()
                            .withDirectives(listOf(directive))
                            .withShouldEndSession(false)
                            .build()
                }
            }
        }

        val intentName = getIntent(input)

        return if (this.buildInIntents.contains(intentName)) {
            when (intentName) {
                LAUNCH_INTENT -> this.application.respondToStart().toSpeechlet(input?.getResponseBuilder()!!)
                STOP_INTENT -> this.application.respondToEnd().toSpeechlet(input?.getResponseBuilder()!!)
                CANCEL_INTENT -> this.application.respondToEnd().toSpeechlet(input?.getResponseBuilder()!!)
                HELP_INTENT -> this.application.respondToHelp().toSpeechlet(input?.getResponseBuilder()!!)
                else -> throw Exception("Impossible")
            }
        } else {
            val action = this.application.listOfActions().get(intentName)
                        ?: throw Exception("Invalid speechlet")
            action(slotValues).toSpeechlet(input?.getResponseBuilder()!!)
        }
    }

}

class Factory {

    companion object {
        fun getApp() : ApplicationAdapter {
            return ApplicationAdapter(ApplicationDemo())
        }
    }
}

class EntryPoint : SkillStreamHandler(getSkill()) {

    companion object {
        fun getSkill() =
            Skills.standard()
                    .addRequestHandlers(Factory.getApp())
                    .withSkillId(System.getenv("ALEXA_ID"))
                    .build()
    }
}