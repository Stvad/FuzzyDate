package org.stvad.fuzzy

import org.stvad.fuzzy.alfred.AlfredResult
import org.stvad.fuzzy.extlib.alfy
import org.stvad.fuzzy.extlib.chrono
import org.stvad.fuzzy.extlib.dateFormat
import org.stvad.fuzzy.extlib.process
import kotlin.js.Date

fun main() {
    alfy.output(listOfNotNull(fuzzyDate(), recurringDate()))
}

val chronoOptions = object {
    val forwardDate = true
}

val extractedDate = chrono.parseDate(alfy.input, options = chronoOptions)

fun fuzzyDate(): AlfredResult {
    val date = extractedDate ?: return AlfredResult("I don't know this one \uD83D\uDE3F")

    val dateString = formatDate(date)
    return AlfredResult(dateString, arg = dateString)
}

fun formatDate(date: Date) = dateFormat(date, process.env.dateFormat as String)

fun recurringDate(): AlfredResult? {
    extractedDate ?: return null

    val parts = alfy.input.split(";")
    if (parts.size < 2) return null

    val repeats = parts[1].toIntOrNull() ?: return null

    val dates = generateSequence(extractedDate) {
        chrono.parseDate(alfy.input, it, chronoOptions)
    }.take(repeats).map { formatDate(it) }

    val dateString = dates.joinToString(process.env.repeatedSeparator)

    return AlfredResult("Repeated", dates.joinToString(" "), arg = dateString)
}
