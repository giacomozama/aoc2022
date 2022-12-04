package me.giacomozama.adventofcode2022.days

object AdventCalendar {

    private val days = mapOf(
        1 to ::Day1,
        2 to ::Day2,
        3 to ::Day3,
        4 to ::Day4
    )

    fun getForDay(day: Int) = days[day]?.invoke()
}