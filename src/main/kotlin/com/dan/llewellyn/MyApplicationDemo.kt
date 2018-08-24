package com.dan.llewellyn

class MyApplicationDemo : MyApplication() {
    override fun getProperties(): AppProperties {

        return AppProperties(
                isAccountLinkingRequired = false
        )
    }

}
