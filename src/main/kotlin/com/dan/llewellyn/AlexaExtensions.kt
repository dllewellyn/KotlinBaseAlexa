package com.dan.llewellyn

import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.*


/**
 * Convert a link request to speechlet
 */
fun AppLinkCard.toSpeechlet() : SpeechletResponse {
    val card = com.amazon.speech.ui.LinkAccountCard()
    card.title = this.title

    val outputSpeech = PlainTextOutputSpeech()
    outputSpeech.text = this.linkCardText

    return SpeechletResponse.newTellResponse(outputSpeech, card)
}

fun AppWelcomeCard.toAskSpeechlet() : SpeechletResponse {
    return newResponse(this.cardText, this.cardText, "", false,  true, ResponseType.ASK_RESPONSE)
}

fun Response.toSpeechlet() : SpeechletResponse {
    return newResponse(this.outputSpeech, this.reprompt, null, cardRequired = this.withCard, type = this.responseType)
}


/**
 * Wrapper for creating the Ask response from the input strings.
 *
 * @param stringOutput
 * the output to be spoken
 * @param repromptText
 * the reprompt for if the user doesn't reply or is misunderstood.
 * @return SpeechletResponse the speechlet response
 */
private fun newResponse(stringOutput: String, repromptText: String?,
                        linkUrl : String?,
                        linkRequired : Boolean = false,
                        cardRequired : Boolean = false,
                        type : ResponseType):
        SpeechletResponse {

    val outputSpeech: OutputSpeech
    var reprompt : Reprompt? = null

    outputSpeech = PlainTextOutputSpeech()
    outputSpeech.text = stringOutput


    repromptText?.let {
        val repromptOutputSpeech = PlainTextOutputSpeech().apply {
            this.text = it
        }
        reprompt = Reprompt().apply {
            this.outputSpeech = repromptOutputSpeech
        }
    }


    var card : Card? = null

    if (cardRequired) {
        if (linkRequired) {
            card = LinkAccountCard()
            card.title = linkUrl
        } else {
            card = SimpleCard()
            card.content = linkUrl
        }
    }

    return when (type) {
        ResponseType.ASK_RESPONSE -> if (card == null) {
            SpeechletResponse.newAskResponse(outputSpeech, reprompt)
        }
        else {
            SpeechletResponse.newAskResponse(outputSpeech, reprompt, card)
        }

        ResponseType.TELL_RESPONSE -> if (card == null) {
            SpeechletResponse.newTellResponse(outputSpeech, card)
        } else {
            SpeechletResponse.newTellResponse(outputSpeech)
        }
    }
}