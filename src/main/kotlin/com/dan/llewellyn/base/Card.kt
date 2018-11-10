package com.dan.llewellyn.base

data class Card(var title : String? = null, var text : String? = null)

fun card(block : Card.() -> Unit) : Card {
    val card = Card()
    card.block()
    return card
}