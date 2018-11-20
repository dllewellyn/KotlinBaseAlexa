package com.dan.llewellyn.interfaces

import com.dan.llewellyn.interfaces.Application

abstract class AbstractApplication : Application {

    lateinit var _sessionId : String

    lateinit var _userId : String

    lateinit var _attributes : Map<String, Any>

    override fun setSessionId(sessionId : String) {
        this._sessionId = sessionId
    }

    override fun getSessionId(): String = this._sessionId

    override fun userId() = _userId

    override fun setUserId(userId : String) {
        this._userId = userId
    }

    override fun updateAttributes(attributes: Map<String, Any>) {
        this._attributes = attributes
    }

    override fun getAttributes(): Map<String, Any> = this._attributes
}