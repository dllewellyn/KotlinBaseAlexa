package com.dan.llewellyn

import com.amazon.ask.SkillStreamHandler
import com.amazon.ask.Skills
import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.DialogState
import com.amazon.ask.model.Response

import com.dan.llewellyn.interfaces.Application
import java.util.*
import com.amazon.ask.model.Intent
import com.amazon.ask.model.IntentRequest
import com.amazon.ask.model.dialog.DelegateDirective
import java.lang.Exception


class ApplicationAdapter(val application : Application) : RequestHandler {

    val buildInIntents = listOf("amazon.helpintent", "amazon.launchintent", "amazon.cancelintent", "amazon.stopintent")

    fun getIntent(input : HandlerInput?) : Intent {
        val request = input?.requestEnvelope?.request
        val intentRequest = request as IntentRequest
        return intentRequest.intent
    }

    override fun canHandle(input: HandlerInput?): Boolean =
            this.application.listOfActions().containsKey(getIntent(input).name) ||
                    this.buildInIntents.contains(getIntent(input).name)


    override fun handle(input: HandlerInput?): Optional<Response> {
        val intent = getIntent(input)
        val slotValues = intent.slots
                .map { it.key to it.value.value}
                .associateBy ({it.first}, {it.second})

        input?.requestEnvelope?.let {
            requestEnvelope ->

            val request = requestEnvelope.request as IntentRequest
            if (request.dialogState == DialogState.STARTED
                    || request.dialogState == DialogState.IN_PROGRESS) {

                val directive = DelegateDirective.builder()
                        .withUpdatedIntent(intent)
                        .build()

                Response.builder()
                        .withDirectives(listOf(directive))
                        .withShouldEndSession(false)
                        .build()
            }
        }

        val action = this.application.listOfActions().get(intent.name)
                ?: throw Exception("Invalid speechlet")
        return action(slotValues).toSpeechlet(input?.getResponseBuilder()!!)
    }

}

class Factory {

    companion object {
        fun getApp() : ApplicationAdapter {
            return ApplicationAdapter(ApplicationDemo())
        }
    }
}

class EntryPoint(val application : Application) : SkillStreamHandler(getSkill()) {

    companion object {
        fun getSkill() =
            Skills.standard()
                    .addRequestHandlers(Factory.getApp())
                    .withSkillId(System.getenv("ALEXA_ID"))
                    .build()
    }
}