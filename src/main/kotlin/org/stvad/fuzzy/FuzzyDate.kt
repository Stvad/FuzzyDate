package org.stvad.fuzzy

import org.stvad.fuzzy.alfred.AlfredResult
import org.stvad.fuzzy.extlib.alfy
import org.stvad.fuzzy.extlib.chrono
import org.stvad.fuzzy.extlib.dateFormat
import org.stvad.fuzzy.extlib.process

fun main() {
    alfy.output(listOf(fuzzyDate()))
}

fun fuzzyDate(): AlfredResult {
    val date = chrono.parseDate(alfy.input, options = object {
        val forwardDate = true
    }) ?: return AlfredResult("I don't know this one \uD83D\uDE3F")

    val dateString = dateFormat(date, process.env.dateFormat as String)
    return AlfredResult(dateString, arg = dateString)
}