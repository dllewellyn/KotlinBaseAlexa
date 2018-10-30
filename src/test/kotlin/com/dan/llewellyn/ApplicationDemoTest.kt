package com.dan.llewellyn

import junit.framework.Assert.assertEquals
import org.junit.Test


internal class ApplicationDemoTest {

    @Test
    fun testCallingListOfActionsForDemo() {
        val slots  = listOf<Pair<String, String>>()

        with (ApplicationDemo()) {

            val actions = this.listOfActions().get("DemoIntent")?: throw IllegalArgumentException()
            assertEquals(actions(slots),
                    Response("Demo intent", ResponseType.TELL_RESPONSE, true))
        }
    }
}