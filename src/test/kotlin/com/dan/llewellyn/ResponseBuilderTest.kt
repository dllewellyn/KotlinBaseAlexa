package com.dan.llewellyn

import com.dan.llewellyn.base.card
import com.dan.llewellyn.base.response
import org.junit.Test

class ResponseBuilderTest {

    @Test
    fun testBuildingAResponseWithResponseBuilderBlocks() {

        response (speech = "Test speech") {
            card = card {
                title = "The title"
                text = "the text"
            }

        }
    }

}