package org.stvad.fuzzy.extlib

import kotlin.js.Date

@JsModule("chrono-node")
external object chrono {
    fun parseDate(input: String, referenceDate: Date? = definedExternally, options: dynamic = definedExternally): Date?
}