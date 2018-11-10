package com.dan.llewellyn

import com.amazon.ask.model.Response
import com.amazon.ask.response.ResponseBuilder
import com.dan.llewellyn.base.AppLinkCard
import com.dan.llewellyn.base.ResponseType
import java.util.*


fun com.dan.llewellyn.base.Resp.toSpeechlet(builder : ResponseBuilder): Optional<Response> {
    var temp = builder

    if (this.reprompt != null) {
        temp = temp.withReprompt(this.reprompt)
    }

    this.card?.let {
        card ->
            temp = temp.withSimpleCard(card.title, card.text)
    }

    temp = temp.withSpeech(this.speech)
                .withShouldEndSession(this.endSession)

    return temp.build()
}


