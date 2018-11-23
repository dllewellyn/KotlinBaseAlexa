package com.dan.llewellyn

import com.amazon.ask.model.Response
import com.amazon.ask.model.dialog.ElicitSlotDirective
import com.amazon.ask.model.interfaces.display.RenderTemplateDirective
import com.amazon.ask.model.ui.PlainTextOutputSpeech
import com.amazon.ask.model.ui.Reprompt
import com.amazon.ask.model.ui.SimpleCard

fun com.dan.llewellyn.base.Response.toSpeechlet(display : Boolean = false): Response {

    var temp =  Response.builder()

    if (this.reprompt != null) {
        temp = temp.withReprompt(Reprompt.builder()
                .withOutputSpeech(PlainTextOutputSpeech.builder()
                        .withText(this.reprompt)
                        .build())
                .build())
    }

    this.card?.let {
        card ->
            temp = temp.withCard(
                    SimpleCard.builder().withTitle(card.title)
                            .withContent(card.text)
                            .build()
            )
    }

    temp = temp.withOutputSpeech(
            PlainTextOutputSpeech.builder().withText(this.speech).build())
                .withShouldEndSession(this.endSession)

    this.directives?.let {
        directives ->
            directives.forEach {

                temp = temp.addDirectivesItem(
                        ElicitSlotDirective.builder()
                                .withSlotToElicit(it.slotToElicit)
                                .build()
                )
            }
    }

    if (display) {
        this.template?.let {
            temp = temp.addDirectivesItem(RenderTemplateDirective
                    .builder()
                    .withTemplate(it.build())
                    .build())
        }
    }

    val t = temp.build()
    println(t.directives)
    return t
}


