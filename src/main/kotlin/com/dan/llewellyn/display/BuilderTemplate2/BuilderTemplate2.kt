package com.dan.llewellyn.display.BuilderTemplate2

import com.amazon.ask.model.interfaces.display.*

enum class TemplateType {
    BodyTemplate1,
    BodyTemplate2,
    BodyTemplate3,
    BodyTemplate6,
    BodyTemplate7
}

data class BuilderTemplate (val title : String,
                            val primaryText : String,
                            val template : TemplateType,
                            var showBack : Boolean = false,
                            val secondaryText : String? = null,
                            val tertiaryText : String? = null) {

    fun build() : Template {

        val textContent = TextContent.builder()
                .withPrimaryText(RichText.builder().withText(primaryText).build())
                .withSecondaryText(RichText.builder().withText(secondaryText).build())
                .withTertiaryText(RichText.builder().withText(tertiaryText).build())
                .build()

        return when(this.template) {
             TemplateType.BodyTemplate2 -> BodyTemplate2.builder()
                    .withTitle(title)
                    .withTextContent(textContent)
                     .withToken("RichText")
                    .build()

            TemplateType.BodyTemplate1 -> BodyTemplate1.builder()
                    .withTitle(title)
                    .withTextContent(textContent)
                    .withToken("RichText")
                    .build()

            TemplateType.BodyTemplate3 -> BodyTemplate3.builder()
                    .withTitle(title)
                    .withTextContent(textContent)
                    .withToken("RichText")
                    .build()

            TemplateType.BodyTemplate6 -> BodyTemplate6.builder()
                    .withTextContent(textContent)
                    .withToken("RichText")
                    .build()
            TemplateType.BodyTemplate7 -> BodyTemplate7.builder()
                    .withTitle(title)
                    .withToken("RichText")
                    .build()
        }
    }

}