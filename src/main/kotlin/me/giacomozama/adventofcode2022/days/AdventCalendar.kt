package me.giacomozama.adventofcode2022.days

object AdventCalendar {

    private val days = mapOf(
        1 to ::Day1,
        2 to ::Day2,
    )

    fun getForDay(day: Int) = days[day]?.invoke()
}