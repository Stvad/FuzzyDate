package org.stvad.fuzzy.extlib

import org.stvad.fuzzy.alfred.AlfredResult

@JsModule("alfy")
external object alfy {
    val input: String
    fun output(items: List<AlfredResult>)
}