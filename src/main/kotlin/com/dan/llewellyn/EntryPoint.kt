package com.dan.llewellyn

import com.amazon.ask.SkillStreamHandler
import com.amazon.ask.Skills
import com.amazon.ask.attributes.AttributesManager
import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.*

import com.dan.llewellyn.interfaces.Application
import java.util.*
import com.amazon.ask.model.dialog.DelegateDirective
import com.amazon.ask.model.interfaces.display.BodyTemplate3
import com.amazon.ask.model.interfaces.display.DisplayInterface
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


    fun setAttributes(req : AttributesManager) {
        val attrs = req.sessionAttributes

        if (attrs != null) {
            this.application.updateAttributes(attrs)
        } else {
            this.application.updateAttributes(mapOf())
        }
    }

    fun handleDialogInterface(requestEnvelope: RequestEnvelope) {
        if (requestEnvelope.request is IntentRequest) {

            val request = requestEnvelope.request as IntentRequest

            if (!this.application.getProperties().handleMissingSlots && (request.dialogState == DialogState.STARTED
                            || request.dialogState == DialogState.IN_PROGRESS)) {

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

    fun getSlotValues(input: HandlerInput?) =
            if (input?.request is IntentRequest &&
                    (input.request as IntentRequest).intent.slots != null ) {
                (input.request as IntentRequest).intent.slots
                        .map { it.key to it.value.value }
                        .associateBy({ it.first }, { it.second })
            }
            else
                mapOf()

    override fun handle(input: HandlerInput?): Optional<Response> {

        input?.requestEnvelope?.session?.sessionId?.let {
            sessionId ->
                this.application.setSessionId(sessionId)
        }

        val slotValues = getSlotValues(input)

        //input?.requestEnvelope?.context?.system?.device?.supportedInterfaces?.
        input?.attributesManager?.let {
            setAttributes(it)
        }

        input?.requestEnvelope?.let { requestEnvelope ->
            handleDialogInterface(requestEnvelope)
        }

        val intentName = getIntent(input)

        return if (this.buildInIntents.contains(intentName)) {
            when (intentName) {
                LAUNCH_INTENT -> Optional.of(this.application.respondToStart().toSpeechlet())
                STOP_INTENT -> Optional.of(this.application.respondToEnd().toSpeechlet())
                CANCEL_INTENT -> Optional.of(this.application.respondToEnd().toSpeechlet())
                HELP_INTENT -> Optional.of(this.application.respondToHelp().toSpeechlet())
                else -> throw Exception("Impossible")
            }
        } else {
            val action = this.application.listOfActions().get(intentName)
                        ?: throw Exception("Invalid speechlet")

            val resp = action(slotValues)

            this.application.getAttributes().forEach {
                vals ->
                input?.attributesManager?.sessionAttributes?.put(vals.key, vals.value)
                    println("Values $vals")
            }

            Optional.of(resp.toSpeechlet())
        }
    }

}