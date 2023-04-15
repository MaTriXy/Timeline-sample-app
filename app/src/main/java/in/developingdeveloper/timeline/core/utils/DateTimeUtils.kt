package `in`.developingdeveloper.timeline.core.utils

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

fun Long.toLocalDateTime(): LocalDateTime {
    return Instant.ofEpochMilli(this).atZone(
        ZoneId.systemDefault(),
    ).toLocalDateTime()
}

fun Long.toLocalDate(): LocalDate {
    return this.toLocalDateTime().toLocalDate()
}

fun LocalDateTime.toEpochMilli(): Long {
    return this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

fun LocalDate.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.of(this, LocalTime.MIDNIGHT)
}
