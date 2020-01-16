package org.stvad.fuzzy.extlib

import kotlin.js.Date

@JsModule("dateFormat")
external fun dateFormat(date: Date?, formatString: String): String