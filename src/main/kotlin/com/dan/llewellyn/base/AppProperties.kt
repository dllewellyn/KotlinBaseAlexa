package com.dan.llewellyn.base

/**
 * Properties to implement for the application
 */
data class AppProperties(val isAccountLinkingRequired : Boolean = false, val handleMissingSlots : Boolean = false)
