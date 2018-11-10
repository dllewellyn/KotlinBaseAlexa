package com.dan.llewellyn

import org.junit.Test


internal class ApplicationDemoTest {

    @Test
    fun testCallingListOfActionsForDemo() {
        val slots  = listOf<Pair<String, String>>()

        with (ApplicationDemo()) {

            val actions = this.listOfActions().get("demointent")?: throw IllegalArgumentException()
            assertEquals(actions(slots),
                    Response("Demo intent", ResponseType.TELL_RESPONSE, true))
        }
    }
}